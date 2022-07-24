import computation.contextfreegrammar.*;
import java.util.ArrayList;
import java.util.List;

public class MyGrammar {
	public static ContextFreeGrammar makeGrammar() {
		// You can write your code here to make the context-free grammar from the assignment

    // Variable objects
    Variable S0 = new Variable("S0");
    Variable S = new Variable("S");
    Variable T = new Variable("T");
    Variable F = new Variable("F");
    Variable S1 = new Variable("S1");
    Variable S2 = new Variable("S2");
    Variable S3 = new Variable("S3");
    Variable L = new Variable("L");
    Variable R = new Variable("R");
    Variable P = new Variable("P");
    Variable M = new Variable("M");

    // Terminal objects
    Terminal one = new Terminal('1');
    Terminal zero = new Terminal('0');
    Terminal x = new Terminal('x');
    Terminal left_br = new Terminal('(');
    Terminal right_br = new Terminal(')');
    Terminal plus = new Terminal('+');
    Terminal times = new Terminal('*');

    // Rules
    Rule S0_1 = new Rule(S0, new Word(S, S1));
    Rule S0_2 = new Rule(S0, new Word(T, S2));
    Rule S0_3 = new Rule(S0, new Word(L, S3));
    Rule S0_4 = new Rule(S0, new Word(one));
    Rule S0_5 = new Rule(S0, new Word(zero));
    Rule S0_6 = new Rule(S0, new Word(x));

    Rule S_1 = new Rule(S, new Word(S, S1));
    Rule S_2 = new Rule(S, new Word(T, S2));
    Rule S_3 = new Rule(S, new Word(L, S3));
    Rule S_4 = new Rule(S, new Word(one));
    Rule S_5 = new Rule(S, new Word(zero));
    Rule S_6 = new Rule(S, new Word(x));

    Rule T_1 = new Rule(T, new Word(T, S2));
    Rule T_2 = new Rule(T, new Word(L, S3));
    Rule T_3 = new Rule(T, new Word(one));
    Rule T_4 = new Rule(T, new Word(zero));
    Rule T_5 = new Rule(T, new Word(x));

    Rule F_1 = new Rule(F, new Word(L, S3));
    Rule F_2 = new Rule(F, new Word(one));
    Rule F_3 = new Rule(F, new Word(zero));
    Rule F_4 = new Rule(F, new Word(x));

    Rule S1r = new Rule(S1, new Word(P, T));
    Rule S2r = new Rule(S2, new Word(M, F));
    Rule S3r = new Rule(S3, new Word(S, R));

    Rule L_r = new Rule(L, new Word(left_br));
    Rule R_r = new Rule(R, new Word(right_br));
    Rule P_r = new Rule(P, new Word(plus));
    Rule M_r = new Rule(M, new Word(times));

    List<Rule> rules = new ArrayList<Rule>();

    rules.add(S0_1);
    rules.add(S0_2);
    rules.add(S0_3);
    rules.add(S0_4);
    rules.add(S0_5);
    rules.add(S0_6);

    rules.add(S_1);
    rules.add(S_2);
    rules.add(S_3);
    rules.add(S_4);
    rules.add(S_5);
    rules.add(S_6);

    rules.add(T_1);
    rules.add(T_2);
    rules.add(T_3);
    rules.add(T_4);
    rules.add(T_5);

    rules.add(F_1);
    rules.add(F_2);
    rules.add(F_3);
    rules.add(F_4);

    rules.add(S1r);
    rules.add(S2r);
    rules.add(S3r);

    rules.add(L_r);
    rules.add(R_r);
    rules.add(P_r);
    rules.add(M_r);

    ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
		return cfg;
	}
}
