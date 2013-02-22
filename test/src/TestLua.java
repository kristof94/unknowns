import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;


public class TestLua {
	public static void main(String[] args) {
		LuaState luaState = LuaStateFactory.newLuaState();
		luaState.openLibs();
		luaState.LdoFile("sanguosha.lua");
	}
}
