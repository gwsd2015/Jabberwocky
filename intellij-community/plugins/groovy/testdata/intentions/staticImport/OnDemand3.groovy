class Util {
  static doSomething(){}
  static doSomething(def a){}
  static doSomethingElse(def a){}
}

def doSomethingElse(){}

Ut<caret>il.doSomething()
Util.doSomething(2)
Util.doSomethingElse(2)