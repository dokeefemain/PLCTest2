//java while
//ebnf
//<while_loop> --> 'while' '(' <boolexpr> ')' '{' <stmt>';'{<stmt>';'} '}'
public class whileloop {
	lex();
	if(nextToken!='while') {
		error();
	}else {
		lex();
		if(nextToken!='(') {
			error()
		}else {
			lex();
			boolexpr();
			if(nextToken!=')') {
				error();
			}else {
				lex();
				if(nextToken!='{') {
					error();
				}else {
					lex();
					stmt();
					if(nextToken!=';') {
						error();
					}else{
                        lex();
                        if(nextToken!='}'){
                            error();
                        }
                    }
				}
			}
		}
	}
}


//java if stmt
//ebnf
//<if_stmt> --> 'if' '(' <boolexpr> ')' <stmt> ['else' <stmt>]

public class if {
	lex();
	if(nextToken!='if') {
		error();
	}else {
		lex();
		if(nextToken!='(') {
			error()
		}else {
			lex();
			boolexpr();
			if(nextToken!=')') {
				error();
			}else {
				lex();
				stmt();
				if(nextToken=='else') {
					lex();
					stmt();
				}
			}
		}
	}
}

//Mathematical expression
//EBNF
//<S> --> <A>{('+'|'-')<A>}
//<A> --> <B>{('/'|'*'|'%')<B>}
//<B> --> ’1’ | ’2’ | ’3’ | ’4’ | ’5’ | ’6’ | ’7’ | ’8’ | ’9’ | <B>

public class Mathematical(){
	lex();
	if(nextToken!='+' || nextToken!='-'){
		error();
	}
}
public class A(){
	lex();
	if(nextToken!='/' || nextToken!='*' || nextToken!='%'){
		Mathematical();
	}
}
public class B(){
	if(nextToken.isNaN()){
			error();
	}
	else{
		lex();
		while(nextToken!=null){
			A();
			lex();
			if(nextToken.isNaN()){
				error();
			}
		}
	}
	}
}

//Mathematical Assignment Statement
//EBNF var is supposed to represent a variable name
//<S> --> <var> '=' <A>
//<A> --> <B>{('+'|'-')<B>}
//<B> --> <C>{('/'|'*'|'%')<C>}
//<D> --> ’1’ | ’2’ | ’3’ | ’4’ | ’5’ | ’6’ | ’7’ | ’8’ | ’9’ | <D> | <var>

public class stmt(){
	lex();
	if(nextToken!=VAR_CODE){
		error();
	}else{
		lex();
		if(nextToken!='='){
			error();
		}else{
			A();
		}
	}
}

public class A(){
	if(nextToken.isNaN() || nextToken!=VAR_CODE){
			error();
	}else{
		lex();
		while(nextToken!=null){
			B();
			lex();
			if(nextToken.isNaN() || nextToken!=VAR_CODE){
				error();
			}
		}
	}
}

public class B(){
	lex();
	if(nextToken!='/' || nextToken!='*' || nextToken!='%'){
		C();
	}
}

public class C(){
	lex();
	if(nextToken!='+' || nextToken!='-'){
		error();
	}
}

