// @SOURCE:/scratch/sandbox/taggers/taggers/webapp/conf/routes
// @HASH:a85dcdd4329f6500487065b8e11fbde4300d992c
// @DATE:Thu Sep 19 16:37:08 PDT 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_index11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("redirected"))))
        

// @LINE:9
private[this] lazy val controllers_Application_show2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("clients/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:10
private[this] lazy val controllers_Application_formApp3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("form"))))
        

// @LINE:11
private[this] lazy val controllers_Application_process4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("process"))))
        

// @LINE:14
private[this] lazy val controllers_Assets_at5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+"""))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """redirected""","""controllers.Application.index1"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """clients/$id<[^/]+>""","""controllers.Application.show(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """form""","""controllers.Application.formApp"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """process""","""controllers.Application.process"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_index11(params) => {
   call { 
        invokeHandler(controllers.Application.index1, HandlerDef(this, "controllers.Application", "index1", Nil,"GET", """""", Routes.prefix + """redirected"""))
   }
}
        

// @LINE:9
case controllers_Application_show2(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Application.show(id), HandlerDef(this, "controllers.Application", "show", Seq(classOf[String]),"GET", """ Display a client.""", Routes.prefix + """clients/$id<[^/]+>"""))
   }
}
        

// @LINE:10
case controllers_Application_formApp3(params) => {
   call { 
        invokeHandler(controllers.Application.formApp, HandlerDef(this, "controllers.Application", "formApp", Nil,"GET", """""", Routes.prefix + """form"""))
   }
}
        

// @LINE:11
case controllers_Application_process4(params) => {
   call { 
        invokeHandler(controllers.Application.process, HandlerDef(this, "controllers.Application", "process", Nil,"GET", """""", Routes.prefix + """process"""))
   }
}
        

// @LINE:14
case controllers_Assets_at5(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}
    
}
        