// @SOURCE:/scratch/sandbox/taggers/taggers/webapp/conf/routes
// @HASH:a85dcdd4329f6500487065b8e11fbde4300d992c
// @DATE:Thu Sep 19 16:37:08 PDT 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers {

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def index1(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "redirected")
}
                                                

// @LINE:11
def process(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "process")
}
                                                

// @LINE:10
def formApp(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "form")
}
                                                

// @LINE:9
def show(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "clients/" + implicitly[PathBindable[String]].unbind("id", id))
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          

// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def index1 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index1",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "redirected"})
      }
   """
)
                        

// @LINE:11
def process : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.process",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "process"})
      }
   """
)
                        

// @LINE:10
def formApp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.formApp",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "form"})
      }
   """
)
                        

// @LINE:9
def show : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.show",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "clients/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              

// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def index1(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index1(), HandlerDef(this, "controllers.Application", "index1", Seq(), "GET", """""", _prefix + """redirected""")
)
                      

// @LINE:11
def process(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.process(), HandlerDef(this, "controllers.Application", "process", Seq(), "GET", """""", _prefix + """process""")
)
                      

// @LINE:10
def formApp(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.formApp(), HandlerDef(this, "controllers.Application", "formApp", Seq(), "GET", """""", _prefix + """form""")
)
                      

// @LINE:9
def show(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.show(id), HandlerDef(this, "controllers.Application", "show", Seq(classOf[String]), "GET", """ Display a client.""", _prefix + """clients/$id<[^/]+>""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          

// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
                  
      