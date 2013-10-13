/**
 * @date 2011-8-14
 */
package org.adaikiss.kay.trys;

import java.util.Stack;

/**
 * @author hlw
 *
 */
public class Caculator {
	public enum Operator{
		Add,
		Subtract,
		Multiply,
		Divide,
	}

	/**
	 * 1 + 2 * 3 / 4 - 5
	 * 1,2,3,4,/,*,5,-,+
	 * 
	 * 1 + 2 * 3 / 4 + 4 /(1 + 1) + 3 * (3 - 2 / (1 + 1) )
	 * 1 ,2,3,4,/,*, 4,1,1,+,/, 3,3,2,1,1,+,/,-,*, +,+,+
	 * @param express
	 */
	public static double eval(String[] express){
		Stack<String> stk = new Stack<String>();
		for(int i = 0;i<express.length;i++){
			if(isOperator(express[i])){
				String right = stk.pop();
				String left = stk.pop();
				stk.push(caculate(left, right, express[i]));
			}else{
				stk.push(express[i]);
			}
		}
		return Double.valueOf(stk.pop());
	}

	public static boolean isOperator(String str){
		return "/".equals(str)||"*".equals(str)||"+".equals(str)||"-".equals(str);
	}

	public static boolean isOperator(char c){
		switch(c){
		case '+':
		case '-':
		case '*':
		case '/':
			return true;
		default : return false;
		}
	}

	public static String caculate(String left, String right, String operator){
		Operator op;
		if("*".equals(operator)){
			op =  Operator.Multiply;
		}else
		if("/".equals(operator)){
			op =  Operator.Divide;
		}else
		if("+".equals(operator)){
			op =  Operator.Add;
		}else
			op = Operator.Subtract;
		return String.valueOf(caculate(Double.valueOf(left), Double.valueOf(right), op));
	}
	public static double caculate(double left, double right, Operator operator){
		switch(operator){
		case Add:{
			return left + right;
		}
		case Subtract:{
			return left - right;
		}
		case Multiply:{
			return left * right;
		}
		case Divide:{
			return left / right;
		}
		}
		throw new UnsupportedOperationException("不支持的操作!");
	}

	class Express{
		Object left;
		Object right;
		Operator op;
		public Express(Object left, Object right, Operator op){
			this.left = left;
			this.right = right;
			this.op = op;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//1 + 2 * 3 / 4 + 4 /(1 + 1) + 3 * (3 - 2 / (1 + 1) ) = 10.5
		//1 ,2,3,4,/,*, 4,1,1,+,/, 3,3,2,1,1,+,/,-,*, +,+,+
		String[] exp = new String[]{"1" ,"2","3","4","/","*", "4","1","1","+","/", "3","3","2","1","1","+","/","-","*", "+","+","+"};
		System.out.println(eval(exp));
	}

	public static void print(String[] str){
		for(String s:str){
			System.out.print(s);
			System.out.print(',');
		}
		System.out.println();
	}
}
