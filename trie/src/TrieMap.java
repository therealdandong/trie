//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface
import java.util.*;

public class TrieMap implements TrieMapInterface{
  TrieMapNode root;
  
  public TrieMap(){
    root = new TrieMapNode();
  }
  
  //Indirectly recursive method to meet definition of interface
  public void put(String key, String value){
    put(root,key,value,0);

  }
  
  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public void put(TrieMapNode current, String curKey, String value,int index){
    if(index == value.length()-1){

      if(current.getChildren().containsKey(curKey.charAt(index))){
        current.getChildren().get(curKey.charAt(index)).setValue(value);
      }else{
        TrieMapNode endNode = new TrieMapNode();
        endNode.setValue(value);
        current.getChildren().put(curKey.charAt(index),endNode);
      }

    }else{
      TrieMapNode nextNode;
      if(current.getChildren().containsKey(curKey.charAt(index))){
        nextNode = current.getChildren().get(curKey.charAt(index++));
      }else{
        nextNode= new TrieMapNode();
        current.getChildren().put(curKey.charAt(index++),nextNode);
      }


      put(nextNode,curKey,value,index);
    }
  }










  //Indirectly recursive method to meet definition of interface
  public String get(String key){
    return get(root,key,0);
  }


















  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public String get(TrieMapNode current, String curKey,int index){
    // for the case it exist.
    // for the case it exist path but not word.
    // for the case it doesn't have exist path.
    if(index == curKey.length()){
      if(current.getValue()!=null){
        return current.getValue();
      }else{
        return null;
      }
    }
    //printChildren(current);
    if(!current.getChildren().containsKey(curKey.charAt(index))){
      return null;
    }
    int num = index+1;
    return get(current.getChildren().get(curKey.charAt(index)),curKey,num);
  }




  private void printChildren(TrieMapNode node){
    for(Character nodes: node.getChildren().keySet()){
      System.out.print(nodes);
    }
    System.out.println(" ");
  }




  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key){
    return containsKey(root,key,0);
  }






  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public boolean containsKey(TrieMapNode current, String curKey,int index){
    if(index == curKey.length()){
      return current.getValue() != null;
    }
    //printChildren(current);
    if(!current.getChildren().containsKey(curKey.charAt(index))){
      return false;
    }
    int num = index+1;
    return containsKey(current.getChildren().get(curKey.charAt(index)),curKey,num);
  }




  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix){
    ArrayList<String> answerList = new ArrayList<String>();
    TrieMapNode nodes = findNode(root,prefix,0);
    getSubtreeKeys(nodes,answerList);

    return answerList;
  }
  
  //Recursive helper function to find node that matches a prefix
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public TrieMapNode findNode(TrieMapNode current, String curKey,int index){
    // exception base case

    if(curKey.length() == 1){
      if(current.getChildren().containsKey(curKey.charAt(0))){
        return current.getChildren().get(curKey.charAt(index));
      }
    }
    // base case
    if(index == curKey.length()){
        return current;
    }
    if(!current.getChildren().containsKey(curKey.charAt(index))){
      return null;
    }
    return findNode(current.getChildren().get(curKey.charAt(index)),curKey,index+1);


  }








  //Recursive helper function to get all keys in a node's subtree
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public void getSubtreeKeys(TrieMapNode current,ArrayList<String> answerList) {
    // assume the above function is correct.
    if(current == null){
      return;
    }
    if(current.getValue()!=null){
      answerList.add(current.getValue());
    }
    if (current.getChildren().isEmpty()) {
      return;
    }else{
      for(TrieMapNode nodes : current.getChildren().values()) {
        getSubtreeKeys(nodes,answerList);
      }
    }
  }
  
  //Indirectly recursive method to meet definition of interface
  public void print(){
    print(root);
  }
  
  //Recursive method to print values in tree
  public void print(TrieMapNode current){
    if(current.getValue()!=null){
      System.out.println(current.getValue());
    }
    if(current.getChildren().isEmpty()){
      return;
    }else{
      for(TrieMapNode nodes:current.getChildren().values()){

        print(nodes);


      }
    }
  }
//
//  public static void main(String[] args){
//    //You can add some code in here to test out your TrieMap initially
//    //The TrieMapTester includes a more detailed test
//
//    ArrayList<String> posWords = new ArrayList<>(){};
//
//    posWords.add("a");
//    posWords.add("b");
//    posWords.add("c");
//    posWords.add("d");
//    posWords.add("e");
//    posWords.add("f");
//    posWords.add("g");
//    posWords.add("ass");
//    //Add all of the positive words
//
//    TrieMap maps = new TrieMap();
//    for(String word: posWords){
//      maps.put(word, word);
//    }
//
//    //System.out.println(maps.get("apple"));
//    //System.out.println(maps.get("ab"));
//    //System.out.println(maps.get("ass"));
//    //.out.println(maps.get("dicks"));
//    //System.out.println(maps.get("dickhead"));
//    //maps.print();
//    for(String output:maps.getKeysForPrefix("dick")){
//      System.out.println(output);
//    }
//  }

}