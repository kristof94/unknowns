package test.android;

import test.android.calculator.Calculator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.TextView;

public class TestCalculator extends Activity {
	private  int defaultMaxLength = 12;
	private EditText displayBar;
	private TextView m;
	private Calculator calculator;
	/**
	 * 处理按一次键响应两次的变量
	 */
	private boolean flag = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initInterface();
    }
    
    /**
     * 初始化界面
     */
    public void initInterface() {
    	displayBar = (EditText)findViewById(R.id.EditTextDisplayBar);
    	m = (TextView)findViewById(R.id.TextViewM);
    	calculator = new Calculator(defaultMaxLength);
    	displayBar.setKeyListener(new KeyListener() {
			@Override
			public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
				return false;
			}
			
			@Override
			public boolean onKeyOther(View view, Editable text, KeyEvent event) {
				return false;
			}
			
			@Override
			public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
				return false;
			}
			
			@Override
			public int getInputType() {
				return InputType.TYPE_NULL;
			}
			
			@Override
			public void clearMetaKeyState(View view, Editable content, int states) {
				displayBar.setText(calculator.backspace());
			}
		});
    	displayBar.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				//每按一次键会执行两次该方法
				Log.i("code", ""+keyCode);
				if(flag) {
					flag = false;
					switch(keyCode) {
					case KeyEvent.KEYCODE_0:
						displayBar.setText(calculator.appendNumber(0));
						break;
					case KeyEvent.KEYCODE_1:
						displayBar.setText(calculator.appendNumber(1));
						break;
					case KeyEvent.KEYCODE_2:
						displayBar.setText(calculator.appendNumber(2));
						break;
					case KeyEvent.KEYCODE_3:
						displayBar.setText(calculator.appendNumber(3));
						break;
					case KeyEvent.KEYCODE_4:
						displayBar.setText(calculator.appendNumber(4));
						break;
					case KeyEvent.KEYCODE_5:
						displayBar.setText(calculator.appendNumber(5));
						break;
					case KeyEvent.KEYCODE_6:
						displayBar.setText(calculator.appendNumber(6));
						break;
					case KeyEvent.KEYCODE_7:
						displayBar.setText(calculator.appendNumber(7));
						break;
					case KeyEvent.KEYCODE_8:
						displayBar.setText(calculator.appendNumber(8));
						break;
					case KeyEvent.KEYCODE_9:
						displayBar.setText(calculator.appendNumber(9));
						break;
					case KeyEvent.KEYCODE_DEL:
						displayBar.setText(calculator.backspace());
						break;
					}
				} else {
					flag = true;
				}
				return true;
			}
		});
    	//不弹出软键盘
    	displayBar.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
    		    int inType = displayBar.getInputType(); // backup the input type
    		    displayBar.setInputType(InputType.TYPE_NULL); // disable soft input
    		    displayBar.onTouchEvent(event); // call native handler
    		    displayBar.setInputType(inType); // restore input type
    		    displayBar.setSelection(displayBar.getText().length());
				return true;
			}
		});
    	findViewById(R.id.Button0).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				displayBar.setText(calculator.appendNumber(0));
			}
		});
    	findViewById(R.id.Button1).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(1));
    		}
    	});
    	findViewById(R.id.Button2).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(2));
    		}
    	});
    	findViewById(R.id.Button3).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(3));
    		}
    	});
    	findViewById(R.id.Button4).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(4));
    		}
    	});
    	findViewById(R.id.Button5).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(5));
    		}
    	});
    	findViewById(R.id.Button6).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(6));
    		}
    	});
    	findViewById(R.id.Button7).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(7));
    		}
    	});
    	findViewById(R.id.Button8).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(8));
    		}
    	});
    	findViewById(R.id.Button9).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.appendNumber(9));
    		}
    	});
    	findViewById(R.id.ButtonDecimal).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.isDecimal());
    		}
    	});
    	findViewById(R.id.ButtonNegative).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.isNegative());
    		}
    	});
    	findViewById(R.id.ButtonPlus).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.operator('+'));
    		}
    	});
    	findViewById(R.id.ButtonMinus).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.operator('-'));
    		}
    	});
    	findViewById(R.id.ButtonMultiplication).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.operator('*'));
    		}
    	});
    	findViewById(R.id.ButtonDivision).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.operator('/'));
    		}
    	});
    	findViewById(R.id.ButtonEqual).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.equal());
    		}
    	});
    	findViewById(R.id.ButtonBackspace).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.backspace());
    		}
    	});
    	findViewById(R.id.ButtonCE).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.ce());
    		}
    	});
    	findViewById(R.id.ButtonC).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.c());
    		}
    	});
    	findViewById(R.id.ButtonSqrt).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.sqrt());
    		}
    	});
    	findViewById(R.id.ButtonPercent).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.percent());
    		}
    	});
    	findViewById(R.id.ButtonInverse).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.inverse());
    		}
    	});
    	findViewById(R.id.ButtonMC).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			calculator.mc();
    			m.setText("");
    		}
    	});
    	findViewById(R.id.ButtonMR).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			displayBar.setText(calculator.mr());
    		}
    	});
    	findViewById(R.id.ButtonMS).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			calculator.ms();
    			m.setText("M");
    		}
    	});
    	findViewById(R.id.ButtonMP).setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			calculator.mp();
    			m.setText("M");
    		}
    	});
    }
}