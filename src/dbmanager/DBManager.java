package dbmanager;

import java.util.ArrayList;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.basex.BaseXXQDataSource;

public class DBManager {
	
	public DBManager(){
		
	}
	
	public static void adicionarArquivo(String pathname, String fileName){
		try{
			XQDataSource xqs = new BaseXXQDataSource();
		    xqs.setProperty("serverName", DBConfig.host);
		    xqs.setProperty("port", DBConfig.port);

		    XQConnection conn = xqs.getConnection(DBConfig.user, DBConfig.password);
		    
		    XQExpression xqe = conn.createExpression();
		    xqe.executeCommand("OPEN " + DBConfig.dbName);
		    
		    xqe.executeCommand("ADD TO / " + pathname);
		    
			xqe.executeCommand("CLOSE");
		    
			conn.close();
		} catch (Exception e){ e.printStackTrace();}
	}
	
	public static void deletarArquivo(String pathname){
		try{
			XQDataSource xqs = new BaseXXQDataSource();
		    xqs.setProperty("serverName", DBConfig.host);
		    xqs.setProperty("port", DBConfig.port);

		    XQConnection conn = xqs.getConnection(DBConfig.user, DBConfig.password);
		    
		    XQExpression xqe = conn.createExpression();
		    xqe.executeCommand("OPEN " + DBConfig.dbName);
		    
		    char ap = '"';
		    
		    xqe.executeCommand("DELETE " + ap + pathname + ap);
		    
			xqe.executeCommand("CLOSE");
		    
			conn.close();
		} catch (Exception e){ e.printStackTrace();}
	}
	
	public static ArrayList<String> getDadosDocumentos() throws XQException{
		ArrayList<String> sList = new ArrayList<String>();
		XQDataSource xqs = new BaseXXQDataSource();
		xqs.setProperty("serverName", DBConfig.host);
		xqs.setProperty("port", DBConfig.port);

		XQConnection conn = xqs.getConnection(DBConfig.user, DBConfig.password);
		    
		XQExpression xqe = conn.createExpression();
		xqe.executeCommand("OPEN " + DBConfig.dbName);
		    
		XQResultSequence rs = xqe.executeQuery(consultaDocs());
		while(rs.next())
			sList.add(rs.getItemAsString(null));
						
		xqe.executeCommand("CLOSE");
		    
		conn.close();
		return sList;
	}
	
	public static String consultar(int n, int param1, int param2) throws XQException{
		String consulta = "";
		switch(n){
		case 1: consulta = consulta1();
		break;
		case 2: consulta = consulta2();
		break;
		case 3: consulta = consulta3(param1, param2);
		break;
		case 4: consulta = consulta4(param1, param2);
		break;
		case 5: consulta = consulta5(param1, param2);
		break;
		case 6: consulta = consulta6(param1, param2);
		break;
		case 7: consulta = consulta7(param1, param2);
		break;
		case 8: consulta = consulta8(param1, param2);
		break;
		case 9: consulta = consulta9(param1, param2);
		break;
		case 10: consulta = consulta10(param1, param2);
		break;
		case 11: consulta = consulta11(param1, param2);
		break;
		case 12: consulta = consulta12(param1, param2);
		break;
		case 13: consulta = consulta13(param1, param2);
		break;
		case 14: consulta = consulta14(param1, param2);
		break;
		case 15: consulta = consulta15(param1, param2);
		break;
		case 16: consulta = consulta16(param1, param2);
		break;
		case 17: consulta = consulta17(param1);
		break;
		default:
		}
		XQDataSource xqs = new BaseXXQDataSource();
		xqs.setProperty("serverName", DBConfig.host);
		xqs.setProperty("port", DBConfig.port);

		XQConnection conn = xqs.getConnection(DBConfig.user, DBConfig.password);
		    
		XQExpression xqe = conn.createExpression();
		xqe.executeCommand("OPEN " + DBConfig.dbName);
		    
		XQResultSequence rs = xqe.executeQuery(consulta);
		String result = "";
		while(rs.next())
			result += rs.getItemAsString(null);
			
		xqe.executeCommand("CLOSE");
		    
		conn.close();
		return result;
	}
	
	private static String consultaDocs(){
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$date := $doc/CURRICULO-VITAE/@DATA-ATUALIZACAO "
				+ "order by $name "
				+ "return (data($name), data($date), db:path($doc))";
		return consulta;
	}
	
	private static String params(int p1, int p2){
		char ap = '"';
		String param = "(" + ap + p1 + ap;
		for(int i = p1 + 1; i <= p2; i++){
			param += ", " + ap + i + ap;
		}
		param += ")";
		return param;
	}
	
	private static String consulta1(){
		char ap = '"';
		String consulta = "let $c := collection() "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">Professor</th></tr>, "
				+ "for $doc in $c "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO "
				+ "order by $name "
				+ "return <tr><td colspan=" + ap + "2" + ap + ">{data($name)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta2(){
		char ap = '"';
		String consulta = "let $a := collection()//AREA-DE-ATUACAO/@NOME-DA-ESPECIALIDADE, "
				+ "$b := distinct-values(data($a)) "
				+ "return (<tr><th bgcolor=" + ap + "#A7A7A7" + ap + ">Área</th><th bgcolor=" + ap + "#A7A7A7" + ap + ">Nº de Professores</th></tr>, "
				+ "for $i in $b "
				+ "let $n := count( "
				+ "for $p in collection() "
				+ "where $p//AREA-DE-ATUACAO/@NOME-DA-ESPECIALIDADE = $i "
				+ "return $p) "
				+ "order by $i "
				+ "return <tr><td>{data($i)}</td><td>{$n}</td></tr>)";
		return consulta;
	}
	
	private static String consulta3(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "return $a) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Artigo</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $a in $c "
				+ "let $title := $a/DADOS-BASICOS-DO-ARTIGO/@TITULO-DO-ARTIGO, "
				+ "$year := $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO "
				+ "order by $year, $title "
				+ "return <tr><td>{data($title)}</td><td>{data($year)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta4(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $cl in $doc//CAPITULO-DE-LIVRO-PUBLICADO "
				+ "where $cl/DADOS-BASICOS-DO-CAPITULO/@ANO = (" + params(p1, p2) + ") "
				+ "return $cl) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "3" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Livro</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Capítulo</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $cl in $c "
				+ "let $tl := $cl/DETALHAMENTO-DO-CAPITULO/@TITULO-DO-LIVRO, "
				+ "$tc := $cl/DADOS-BASICOS-DO-CAPITULO/@TITULO-DO-CAPITULO-DO-LIVRO, "
				+ "$al := $cl/DADOS-BASICOS-DO-CAPITULO/@ANO "
				+ "order by $al, $tc "
				+ "return <tr><td>{data($tl)}</td><td>{data($tc)}</td><td>{data($al)}</td></tr>, "
				+ "<tr><td colspan = " + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c)}</td></tr>)"; 
		return consulta;
	}
	
	private static String consulta5(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c1 := (for $p in $doc//PROJETO-DE-PESQUISA "
				+ "where $p/@ANO-FIM = (" + ap + ap + ") "
				+ "return $p), "
				+ "$c2 := (for $p in $doc//PROJETO-DE-PESQUISA "
				+ "where $p/@ANO-FIM = (" + params(p1, p2) + ") "
				+ "return $p) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "for $p in $c1 "
				+ "let $pn := $p/@NOME-DO-PROJETO "
				+ "order by $pn "
				+ "return (<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">{data($pn)}</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Em andamento</th></tr>, "
				+ "for $i in $p//INTEGRANTES-DO-PROJETO/@NOME-COMPLETO "
				+ "where $i != $name "
				+ "order by $i "
				+ "return <tr><td>{data($i)}</td><td>Integrante</td></tr>, "
				+ "for $f in $p//FINANCIADOR-DO-PROJETO/@NOME-INSTITUICAO "
				+ "order by $f "
				+ "return <tr><td>{data($f)}</td><td>Financiador</td></tr>), "
				+ "<tr><td bgcolor=" + ap + "#A7A7A7" + ap + ">Total em andamento:</td><td>{count($c1)}</td></tr>, "
				+ "for $p in $c2 "
				+ "let $pn := $p/@NOME-DO-PROJETO, "
				+ "$pa := $p/@ANO-FIM "
				+ "order by $pa, $pn "
				+ "return (<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">{data($pn)}</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">{data($pa)}</th></tr>, "
				+ "for $i in $p//INTEGRANTES-DO-PROJETO/@NOME-COMPLETO "
				+ "where $i != $name "
				+ "order by $i "
				+ "return <tr><td>{data($i)}</td><td>Integrante</td></tr>, "
				+ "for $f in $p//FINANCIADOR-DO-PROJETO/@NOME-INSTITUICAO "
				+ "order by $f "
				+ "return <tr><td>{data($f)}</td><td>Financiador</td></tr>), "
				+ "<tr><td bgcolor=" + ap + "#A7A7A7" + ap + ">Total concluído:</td><td>{count($c2)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta6(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c1 := (for $v in $doc//ATUACAO-PROFISSIONAL/VINCULOS "
				+ "where $v/@OUTRO-VINCULO-INFORMADO = " + ap + "Revisor de periódico" + ap + " "
				+ "and $v/@ANO-FIM = (" + ap + ap + ") "
				+ "return $v), "
				+ "$c2 := (for $v in $doc//ATUACAO-PROFISSIONAL/VINCULOS "
				+ "where $v/@OUTRO-VINCULO-INFORMADO = " + ap + "Revisor de periódico" + ap + " "
				+ "and $v/@ANO-FIM = (" + params(p1, p2) + ") "
				+ "return $v) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "3" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th colspan =" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Periódico</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano de Início</th></tr>, "
				+ "for $v in $c1 "
				+ "let $pn := $v/../@NOME-INSTITUICAO, "
				+ "$ai := $v/@ANO-INICIO "
				+ "order by $ai, $pn "
				+ "return <tr><td colspan=" + ap + "2" + ap + ">{data($pn)}</td><td>{data($ai)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total em andamento:</td><td>{count($c1)}</td></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Periódico</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano de Início</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano de Fim</th></tr>, "
				+ "for $v in $c2 "
				+ "let $pn := $v/../@NOME-INSTITUICAO, "
				+ "$ai := $v/@ANO-INICIO, "
				+ "$af := $v/@ANO-FIM "
				+ "order by $af, $ai, $pn "
				+ "return <tr><td>{data($pn)}</td><td>{data($ai)}</td><td>{data($af)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total concluído:</td><td>{count($c2)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta7(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $e in $doc//DADOS-BASICOS-DA-ORGANIZACAO-DE-EVENTO "
				+ "where $e/@ANO = (" + params(p1, p2) + ") "
				+ "return $e) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "3" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Evento</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Tipo</th></tr>, "
				+ "for $e in $c "
				+ "let $n := $e/@TITULO, "
				+ "$a := $e/@ANO, "
				+ "$t := $e/@TIPO "
				+ "order by $t, $a, $n "
				+ "return <tr><td>{data($n)}</td><td>{data($a)}</td><td>{data($t)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta8(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $p in $doc//PREMIO-TITULO "
				+ "let $pa := $p/@ANO-DA-PREMIACAO "
				+ "where $pa = (" + params(p1, p2) + ") "
				+ "return $p) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Prêmio</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $p in $c "
				+ "let $pn := $p/@NOME-DO-PREMIO-OU-TITULO, "
				+ "$pa := $p/@ANO-DA-PREMIACAO "
				+ "order by $pa, $pn "
				+ "return <tr><td>{data($pn)}</td><td>{data($pa)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta9(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c1 := (for $o in $doc//ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA "
				+ "return $o), "
				+ "$c2 := (for $o in $doc//OUTRAS-ORIENTACOES-CONCLUIDAS "
				+ "where $o/DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NATUREZA = " + ap + "INICIACAO_CIENTIFICA" + ap + " "
				+ "and $o/DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@ANO = (" + params(p1, p2) + ") "
				+ "return $o) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th  colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">IC em andamento</th></tr>, "
				+ "for $o in $c1 "
				+ "let $no := $o/DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@NOME-DO-ORIENTANDO "
				+ "order by $no "
				+ "return <tr><td colspan =" + ap + "2" + ap + ">{data($no)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total em andamento:</td><td>{count($c1)}</td></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">IC concluída</th><th  bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $o in $c2 "
				+ "let $no := $o/DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NOME-DO-ORIENTADO, "
				+ "$ao := $o/DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@ANO "
				+ "order by $ao, $no "
				+ "return <tr><td>{data($no)}</td><td>{data($ao)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total concluído:</td><td>{count($c2)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta10(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$g := $doc//OUTRAS-ORIENTACOES-CONCLUIDAS, "
				+ "$gn := $g/DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NOME-DO-ORIENTADO, "
				+ "$c1 := (for $ma in $doc//DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO/@NOME-DO-ORIENTANDO "
				+ "where $ma = $gn "
				+ "return $ma), "
				+ "$c2 := (for $mc in $doc//ORIENTACOES-CONCLUIDAS-PARA-MESTRADO "
				+ "where $mc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DO-ORIENTADO = $gn "
				+ "and $mc/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@ANO = (" + params(p1, p2) + ") "
				+ "return $mc), "
				+ "$c3 := (for $da in $doc//DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO/@NOME-DO-ORIENTANDO "
				+ "where $da = $gn "
				+ "return $da), "
				+ "$c4 := (for $dc in $doc//ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO "
				+ "where $dc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DO-ORIENTADO = $gn "
				+ "and $dc/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@ANO = (" + params(p1, p2) + ") "
				+ "return $dc) "
				+ "where $g/DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NATUREZA = " + ap + "TRABALHO_DE_CONCLUSAO_DE_CURSO_GRADUACAO" + ap + " "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Mestrado em andamento</th></tr>, "
				+ "for $ma in $c1 "
				+ "order by $ma "
				+ "return <tr><td colspan=" + ap + "2" + ap +">{data($ma)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total em andamento:</td><td>{count($c1)}</td></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Mestrado Concluído</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $mc in $c2 "
				+ "let $mcn := $mc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DO-ORIENTADO "
				+ "let $mca := $mc/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@ANO "
				+ "order by $mca, $mcn "
				+ "return <tr><td>{data($mcn)}</td><td>{data($mca)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total concluído:</td><td>{count($c2)}</td></tr>, "
				+ "<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Doutorado em andamento</th></tr>, "
				+ "for $da in $c3 "
				+ "order by $da "
				+ "return <tr><td colspan=" + ap + "2" + ap +">{data($da)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total em andamento:</td><td>{count($c3)}</td></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Doutorado Concluído</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $dc in $c4 "
				+ "let $dcn := $dc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DO-ORIENTADO "
				+ "let $dca := $dc/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@ANO "
				+ "order by $dca, $dcn "
				+ "return <tr><td>{data($dcn)}</td><td>{data($dca)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total concluído:</td><td>{count($c4)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta11(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $m in $doc//ORIENTACOES-CONCLUIDAS-PARA-MESTRADO "
				+ "where $m/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@ANO = (" + params(p1, p2) + ") "
				+ "return $m) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Mestre</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $m in $c "
				+ "let $mn := $m/DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DO-ORIENTADO, "
				+ "$ma := $m/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@ANO "
				+ "order by $ma, $mn "
				+ "return  <tr><td>{data($mn)}</td><td>{data($ma)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta12(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c1 := (for $ma in $doc//DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO/@NOME-DO-ORIENTANDO "
				+ "return for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "and $a/AUTORES/@NOME-COMPLETO-DO-AUTOR = $ma "
				+ "return $a), "
				+ "$c2 := (for $mc in $doc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DO-ORIENTADO "
				+ "return for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "and $a/AUTORES/@NOME-COMPLETO-DO-AUTOR = $mc "
				+ "return $a) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "3" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Mestrando</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Artigo</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "let $tit := $a/DADOS-BASICOS-DO-ARTIGO/@TITULO-DO-ARTIGO "
				+ "let $aa := $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO "
				+ "order by $aa, $tit "
				+ "return for $au in $a/AUTORES/@NOME-COMPLETO-DO-AUTOR "
				+ "let $ma := $doc//DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO/@NOME-DO-ORIENTANDO "
				+ "where $au = $ma "
				+ "order by $au "
				+ "return <tr><td>{data($au)}</td><td>{data($tit)}</td><td>{data($aa)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c1)}</td></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Mestre</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Artigo</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "let $tit := $a/DADOS-BASICOS-DO-ARTIGO/@TITULO-DO-ARTIGO "
				+ "let $aa := $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO "
				+ "order by $aa, $tit "
				+ "return for $au in $a/AUTORES/@NOME-COMPLETO-DO-AUTOR "
				+ "let $mc := $doc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DO-ORIENTADO "
				+ "where $au = $mc "
				+ "order by $au "
				+ "return <tr><td>{data($au)}</td><td>{data($tit)}</td><td>{data($aa)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c2)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta13(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $d in $doc//ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO "
				+ "where $d/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@ANO = (" + params(p1, p2) + ") "
				+ "return $d) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Doutor</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $d in $c "
				+ "let $dn := $d/DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DO-ORIENTADO, "
				+ "$da := $d/DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@ANO "
				+ "order by $da, $dn "
				+ "return  <tr><td>{data($dn)}</td><td>{data($da)}</td></tr>, "
				+ "<tr><td bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta14(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c1 := (for $da in $doc//DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO/@NOME-DO-ORIENTANDO "
				+ "return for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "and $a/AUTORES/@NOME-COMPLETO-DO-AUTOR = $da "
				+ "return $a), "
				+ "$c2 := (for $dc in $doc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DO-ORIENTADO "
				+ "return for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "and $a/AUTORES/@NOME-COMPLETO-DO-AUTOR = $dc "
				+ "return $a) "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "3" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Doutorando</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Artigo</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "let $tit := $a/DADOS-BASICOS-DO-ARTIGO/@TITULO-DO-ARTIGO "
				+ "let $aa := $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO "
				+ "order by $aa, $tit "
				+ "return for $au in $a/AUTORES/@NOME-COMPLETO-DO-AUTOR "
				+ "let $da := $doc//DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO/@NOME-DO-ORIENTANDO "
				+ "where $au = $da "
				+ "order by $au "
				+ "return <tr><td>{data($au)}</td><td>{data($tit)}</td><td>{data($aa)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c1)}</td></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Doutor</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Artigo</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $a in $doc//ARTIGO-PUBLICADO "
				+ "where $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "let $tit := $a/DADOS-BASICOS-DO-ARTIGO/@TITULO-DO-ARTIGO "
				+ "let $aa := $a/DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO "
				+ "order by $aa, $tit "
				+ "return for $au in $a/AUTORES/@NOME-COMPLETO-DO-AUTOR "
				+ "let $dc := $doc//DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DO-ORIENTADO "
				+ "where $au = $dc "
				+ "order by $au "
				+ "return <tr><td>{data($au)}</td><td>{data($tit)}</td><td>{data($aa)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td>{count($c2)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta15(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $au in $doc//ARTIGO-PUBLICADO/AUTORES "
				+ "where $au/../DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO = (" + params(p1, p2) + ") "
				+ "and $au/@NOME-COMPLETO-DO-AUTOR != $name "
				+ "return $au) "
				+ "order by $name "
				+ "return (<tr><th colspan =" + ap + "3" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Pesquisador</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Artigo</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano</th></tr>, "
				+ "for $au in $c "
				+ "let $an := $au/@NOME-COMPLETO-DO-AUTOR, "
				+ "$tit := $au/../DADOS-BASICOS-DO-ARTIGO/@TITULO-DO-ARTIGO, "
				+ "$aa := $au/../DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO "
				+ "order by $aa, $tit, $an "
				+ "return <tr><td>{data($an)}</td><td>{data($tit)}</td><td>{data($aa)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total:</td><td bgcolor=" + ap + "#C7C7C7" + ap + ">{count($c)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta16(int p1, int p2){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$nn := collection()//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$c := (for $p in $doc//PROJETO-DE-PESQUISA "
				+ "for $i in $p//INTEGRANTES-DO-PROJETO "
				+ "where $p/@ANO-FIM = (" + ap + ap + ") "
				+ "and $i/@NOME-COMPLETO = $nn "
				+ "and $i/@NOME-COMPLETO != $name "
				+ "return $i), "
				+ "$c1 := (for $p in $doc//PROJETO-DE-PESQUISA "
				+ "for $i in $p//INTEGRANTES-DO-PROJETO "
				+ "where $p/@ANO-FIM = (" + params(p1, p2) + ") "
				+ "and $i/@NOME-COMPLETO = $nn "
				+ "and $i/@NOME-COMPLETO != $name "
				+ "return $i) "
				+ "order by $name "
				+ "return (<tr><th colspan =" + ap + "4" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Pesquisador</th><th colspan =" + ap + "2" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Projeto</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano de Início</th></tr>, "
				+ "for $i in $c "
				+ "let $in := $i/@NOME-COMPLETO, "
				+ "$p := $i/../../@NOME-DO-PROJETO, "
				+ "$a := $i/../../@ANO-INICIO "
				+ "order by $a, $p, $in "
				+ "return <tr><td>{data($in)}</td><td colspan =" + ap + "2" + ap + ">{data($p)}</td><td>{data($a)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "3" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total em andamento:</td><td bgcolor=" + ap + "#C7C7C7" + ap + ">{count($c)}</td></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Pesquisador</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Projeto</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano de Início</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Ano de Fim</th></tr>, "
				+ "for $i in $c1 "
				+ "let $in := $i/@NOME-COMPLETO, "
				+ "$p := $i/../../@NOME-DO-PROJETO, "
				+ "$ai := $i/../../@ANO-INICIO, "
				+ "$af := $i/../../@ANO-FIM "
				+ "order by $af, $ai, $p, $in "
				+ "return <tr><td>{data($in)}</td><td>{data($p)}</td><td>{data($ai)}</td><td>{data($af)}</td></tr>, "
				+ "<tr><td colspan=" + ap + "3" + ap + " bgcolor=" + ap + "#C7C7C7" + ap + ">Total concluído:</td><td bgcolor=" + ap + "#C7C7C7" + ap + ">{count($c1)}</td></tr>)";
		return consulta;
	}
	
	private static String consulta17(int p1){
		char ap = '"';
		String consulta = "for $doc in collection() "
				+ "let $name := $doc//DADOS-GERAIS/@NOME-COMPLETO "
				+ "order by $name "
				+ "return (<tr><th colspan=" + ap + "2" + ap + " bgcolor=" + ap + "#A7A7A7" + ap + ">{data($name)}</th></tr>, "
				+ "<tr><th bgcolor=" + ap + "#C7C7C7" + ap + ">Professor</th><th bgcolor=" + ap + "#C7C7C7" + ap + ">Nº de artigos em conjunto</th></tr>, "
				+ "for $i in 1 to " + p1 + " "
				+ "return (for $p in collection() "
				+ "let $pn := $p//DADOS-GERAIS/@NOME-COMPLETO, "
				+ "$art := $doc//ARTIGO-PUBLICADO "
				+ "where $p != $doc and $pn = $art/AUTORES/@NOME-COMPLETO-DO-AUTOR "
				+ "let $n := count(for $x in $art/AUTORES/@NOME-COMPLETO-DO-AUTOR "
				+ "where $x = $pn "
				+ "return $x) "
				+ "order by $n descending "
				+ "return <tr><td>{data($pn)}</td><td>{data($n)}</td></tr>)[$i])";
		return consulta;
	}
}
