import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import java.util.ArrayList;
import java.util.List;

public class Parser implements IParser {

  public List<Derivation> generateDerivations(ContextFreeGrammar cfg, Word w){
    List<Derivation> active_derivations = new ArrayList<Derivation>();
    List<Rule> rules = cfg.getRules();
    Variable startVariable = cfg.getStartVariable();
    Derivation startDerivation = new Derivation(new Word(startVariable));
    active_derivations.add(startDerivation);

    int steps = 0;
    int n = w.length();
    int totalSteps;
    
    // Checks for empty word
    if(n == 0){
      totalSteps = 1;
    }
    else{
      totalSteps = (2*n)-1;
    }

    while(steps < totalSteps){
      List<Derivation> new_active_derivations = new ArrayList<Derivation>();
      for(Derivation d : active_derivations){
        Word word = d.getLatestWord();
        for(Symbol symbol : word){
          for(Rule rule: rules){
              if(rule.getVariable().equals(symbol)){
                Word expansion = rule.getExpansion();
                int numOfSymbols = word.count(symbol);
                for(int i=0; i<numOfSymbols; i++) {
                  int index = word.indexOfNth(symbol, i);
                  Word newWord = word.replace(index, expansion);
                  Derivation newD = new Derivation(d);
							    newD.addStep(newWord, rule, index);
							    new_active_derivations.add(newD);  
              }
            }
          }
        }
        active_derivations = new_active_derivations;
        for(Derivation der : active_derivations){
          if(w.equals(der.getLatestWord())){
            return active_derivations;
          }
        }
      }
    steps++;
    }
    return active_derivations;
  }

  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){
    // Checks to ensure word only contains terminals
    for(Symbol s : w){
      if(!s.isTerminal()){
        return false;
      }
    }

    List<Derivation> derivations = generateDerivations(cfg, w);
    // Checks to see if word is present in derivations
    for(Derivation d : derivations){
      if(w.equals(d.getLatestWord())){
        return true;
      }
    }
    return false;
  }
  
  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {
    if(isInLanguage(cfg, w)){
      if(w.length() == 0){
        Variable startVariable = cfg.getStartVariable();
        // Creates empty word parse tree
        ParseTreeNode emptyWordTree = ParseTreeNode.emptyParseTree(startVariable);
        return emptyWordTree;
      }

      List<Derivation> treeDerivations = generateDerivations(cfg, w);
      List<ParseTreeNode> treeStructure = new ArrayList<ParseTreeNode>();
      boolean treeFound = false;

      for(Derivation d : treeDerivations){
        if(w.equals(d.getLatestWord())){
          // Prevents printing more than one tree for a word with multiple derivations
          if(treeFound == true){
            break;
          }

          treeFound = true;
          Word lastWord = d.getLatestWord();
          
          for(Symbol symbol : lastWord){
            ParseTreeNode initialTree = new ParseTreeNode(symbol);
            treeStructure.add(initialTree);
          }
          for(Step s : d){
            if(s.isStartSymbol()){
              break;
            }
            else{
              Word expansionWord = s.getRule().getExpansion();

              if(expansionWord.length() == 1){
                ParseTreeNode subTree = new ParseTreeNode(s.getRule().getVariable(), treeStructure.get(s.getIndex()));
                treeStructure.set(s.getIndex(), subTree);
              }
              else if(expansionWord.length() == 2){
                ParseTreeNode subTree = new ParseTreeNode(s.getRule().getVariable(), treeStructure.get(s.getIndex()), treeStructure.get(s.getIndex()+1));
                treeStructure.remove(s.getIndex());
                treeStructure.set(s.getIndex(), subTree);
              }         
            }
          }
        }
        }
        return treeStructure.get(0);
      }
    else{
      return null;
    }
  } 
  }
