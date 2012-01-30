public class Calculator {
	/**
	 * 未输入任何数字时的状态
	 */
	private static final int INIT_STATE = 0;
	
	/**
	 * 输入状态
	 */
	private static final int INPUT_STATE = 1;
	
	/**
	 * 等待重新输入的状态
	 */
	private static final int WAIT_STATE = 2;
	
	/**
	 * 显示结果状态
	 */
	private static final int RESULT_STATE = 3;
	
	/**
	 * 错误状态
	 */
	private static final int ERROR_STATE = 4;
	
	/**
	 * 无
	 */
	private static final int NO_SIGN = 0;
	
	/**
	 * 加
	 */
	private static final int PLUS_SIGN = 1;
	
	/**
	 * 减
	 */
	private static final int MINUS_SIGN = 2;
	
	/**
	 * 乘
	 */
	private static final int MULTIPLICATION_SIGN = 3;
	
	/**
	 * 除
	 */
	private static final int DIVISION_SIGN = 4;
	
	/**
	 * 状态
	 */
	private int state = INIT_STATE;
	
	/**
	 * 运算符
	 */
	private int sign = NO_SIGN;
	
	/**
	 * 是否是小数
	 */
	private boolean isDecimal = false;
	
	/**
	 * 是否是负数
	 */
	private boolean isNegative = false;
	
	/**
	 * 结果数
	 */
	private double resultNumber = 0;
	
	/**
	 * 输入数
	 */
	private double inputNumber = 0;
	
	/**
	 * 保存的数
	 */
	private double m = 0;
	
	private StringBuilder result = new StringBuilder("0.");
	
	/**
	 * 获取显示结果
	 * @return
	 */
	public String getResult() {
		return getResult(true);
	}
	
	public String getResult(boolean isCut) {
		if(isCut) {
			int length = result.length() - 1;
			if(result.charAt(length) == '0') {
				result.deleteCharAt(length);
			}
		}
		return result.toString();
	}
	
	/**
	 * 重置显示结果
	 * @param num
	 */
	private void resetResult(double num) {
		result.delete(0, result.length());
		result.append(num);
	}
	
	/**
	 * 保存输入数
	 */
	private void saveInputNumber() {
		inputNumber = Double.parseDouble(getResult(false));
	}
	
	/**
	 * 输入数字
	 * @param num
	 * @return
	 */
	public String appendNumber(int num) {
		switch(state) {
		case RESULT_STATE:
		case WAIT_STATE:
			isDecimal = false;
			isNegative = false;
		case INIT_STATE:
			state = INPUT_STATE;
			resetResult(num);
			break;
		case INPUT_STATE:
			if(isDecimal) {
				result.append(num);
				return result.toString();
			} else {
				if(num!=0 || result.length()>2)
					result.insert(result.length() - 1, num);
			}
			break;
		}
		return getResult();
	}
	
	/**
	 * 输入小数点
	 */
	public String isDecimal() {
		switch(state) {
		case WAIT_STATE:
		case RESULT_STATE:
			resetResult(0);
		case INIT_STATE:
			state = INPUT_STATE;
		case INPUT_STATE:
			isDecimal = true;
			break;
		}
		return getResult();
	}
	
	/**
	 * 输入负号
	 * @return
	 */
	public String isNegative() {
		switch(state) {
		case INPUT_STATE:
		case RESULT_STATE:
			if(isNegative) {
				result.deleteCharAt(0);
			} else {
				result.insert(0, "-");
			}
			isNegative = !isNegative;
			break;
		}
		return getResult();
	}
	
	/**
	 * 输入+、-、*、/等运算符
	 * @return
	 */
	public String operator(char operator) {
		String result = null;
		switch(state) {
		case INPUT_STATE:
			if(sign==NO_SIGN) {
				switch(operator) {
				case '+':
					sign = PLUS_SIGN;
					break;
				case '-':
					sign = MINUS_SIGN;
					break;
				case '*':
					sign = MULTIPLICATION_SIGN;
					break;
				case '/':
					sign = DIVISION_SIGN;
					break;
				}
			} else {
				result = equal();
			}
		case RESULT_STATE:
			saveInputNumber();
			state = WAIT_STATE;
			break;
		}
		switch(operator) {
		case '+':
			sign = PLUS_SIGN;
			break;
		case '-':
			sign = MINUS_SIGN;
			break;
		case '*':
			sign = MULTIPLICATION_SIGN;
			break;
		case '/':
			sign = DIVISION_SIGN;
			break;
		}
		return result==null?getResult():result;
	}
	
	/**
	 * 输入等号计算结果
	 * @return
	 */
	public String equal() {
		switch(state) {
		case INPUT_STATE:
			state = RESULT_STATE;
			resultNumber = inputNumber;
			saveInputNumber();
			break;
		case WAIT_STATE:
			state = RESULT_STATE;
			saveInputNumber();
			resultNumber = inputNumber;
			break;
		}
		switch(state) {
		case RESULT_STATE:
			result.delete(0, result.length());
			System.out.println(result.toString());
			System.out.println(resultNumber+"");
			System.out.println(inputNumber+"");
			//计算结果
			switch(sign) {
			case PLUS_SIGN:
				resultNumber += inputNumber;
				break;
			case MINUS_SIGN:
				resultNumber -= inputNumber;
				break;
			case MULTIPLICATION_SIGN:
				resultNumber *= inputNumber;
				break;
			case DIVISION_SIGN:
				if(inputNumber == 0) {
					state = ERROR_STATE;
					result.append("除数不能为零。");
				} else {
					resultNumber /= inputNumber;
				}
				break;
			}
			if(state == RESULT_STATE) {
				result.append(resultNumber);
			}
			System.out.println(result.toString());
			System.out.println(resultNumber+"");
			System.out.println(inputNumber+"");
			break;
		}
		return getResult();
	}
	
	/**
	 * 清除当前所有输入
	 * @return
	 */
	public String ce() {
		state = INIT_STATE;
		isDecimal = isNegative = false;
		resetResult(0);
		return getResult();
	}
	
	/**
	 * 清除所有结果
	 * @return
	 */
	public String c() {
		state = INIT_STATE;
		sign = NO_SIGN;
		resultNumber = inputNumber = 0;
		isDecimal = isNegative = false;
		resetResult(0);
		return getResult();
	}
	
	/**
	 * 取消上一次输入的数字
	 * @return
	 */
	public String backspace() {
		switch(state) {
		case INPUT_STATE:
			int size = result.length();
			if(result.charAt(size - 1) == '.') {
				if(isDecimal) {
					isDecimal = false;
				} else {
					if(size > 2) {
						result.deleteCharAt(size - 2);
					} else {
						resetResult(0);
					}
				}
			} else {
				result.deleteCharAt(size - 1);
			}
			break;
		}
		return getResult();
	}
	
	/**
	 * 计算平方根
	 * @return
	 */
	public String sqrt() {
		switch(state) {
		case INPUT_STATE:
		case WAIT_STATE:
		case RESULT_STATE:
			double num = Double.parseDouble(getResult(false));
			result.delete(0, result.length());
			if(num < 0) {
				state = ERROR_STATE;
				result.append("函数输入无效。");
			} else {
				state = RESULT_STATE;
				result.append(Math.sqrt(num));
			}
			break;
		}
		return getResult();
	}
	
	/**
	 * 计算百分数
	 * @return
	 */
	public String percent() {
		switch(state) {
		case INIT_STATE:
		case INPUT_STATE:
		case WAIT_STATE:
		case RESULT_STATE:
			state = RESULT_STATE;
			double num = Double.valueOf(getResult(false));
			resultNumber = inputNumber * num / 100;
			resetResult(resultNumber);
			break;
		}
		return getResult();
	}
	
	/**
	 * 计算倒数
	 * @return
	 */
	public String inverse() {
		switch(state) {
		case INIT_STATE:
		case INPUT_STATE:
		case WAIT_STATE:
		case RESULT_STATE:
			double num = Double.valueOf(getResult(false));
			result.delete(0, result.length());
			if(num == 0) {
				state = ERROR_STATE;
				result.append("除数不能为零。");
			} else {
				state = RESULT_STATE;
				resultNumber = 1 / num;
				result.append(resultNumber);
			}
			break;
		}
		return getResult();
	}
	
	/**
	 * 清除储存的数字
	 */
	public void mc() {
		m = 0;
	}
	
	/**
	 * 载入储存的数字
	 */
	public String mr() {
		switch(state) {
		case INIT_STATE:
		case INPUT_STATE:
		case WAIT_STATE:
		case RESULT_STATE:
			isDecimal = isNegative = false;
			resetResult(m);
			break;
		}
		return getResult();
	}
	
	/**
	 * 储存显示的数字
	 */
	public void ms() {
		switch(state) {
		case INIT_STATE:
		case INPUT_STATE:
		case WAIT_STATE:
		case RESULT_STATE:
			m = Double.valueOf(getResult(false));
			break;
		}
	}
	
	/**
	 * 将当前显示的数字和存储的数字相加后保存
	 */
	public void mp() {
		switch(state) {
		case INIT_STATE:
		case INPUT_STATE:
		case WAIT_STATE:
		case RESULT_STATE:
			m += Double.valueOf(getResult(false));
			break;
		}
	}
}
