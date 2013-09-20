
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object form1 extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[scala.Tuple2[String, String]],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(regexForm: Form[(String,String)]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.36*/("""

"""),_display_(Seq[Any](/*3.2*/main(title = "Title")/*3.23*/{_display_(Seq[Any](format.raw/*3.24*/("""

  """),_display_(Seq[Any](/*5.4*/helper/*5.10*/.form(action = routes.Application.process)/*5.52*/{_display_(Seq[Any](format.raw/*5.53*/("""
  
     """),_display_(Seq[Any](/*7.7*/helper/*7.13*/.inputText(
       field = regexForm("path"),
       args = '_label -> "Path To Taggers Directory", 'size -> 100
     ))),format.raw/*10.7*/("""
     
     """),_display_(Seq[Any](/*12.7*/helper/*12.13*/.textarea(
       field = regexForm("input"),
       args = '_label -> "Input Text: ", 'rows -> 15, 'cols ->100
     ))),format.raw/*15.7*/("""
     
     <p class="buttons">
     	<input type="submit" id="submit">
     <p>
  
  """)))})),format.raw/*21.4*/("""


""")))})))}
    }
    
    def render(regexForm:Form[scala.Tuple2[String, String]]): play.api.templates.Html = apply(regexForm)
    
    def f:((Form[scala.Tuple2[String, String]]) => play.api.templates.Html) = (regexForm) => apply(regexForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Sep 19 16:58:18 PDT 2013
                    SOURCE: /scratch/sandbox/taggers/taggers/webapp/app/views/form1.scala.html
                    HASH: 51c2b0131aa2be9a4b1462504e8f988a22c97ef1
                    MATRIX: 533->1|644->35|681->38|710->59|748->60|787->65|801->71|851->113|889->114|933->124|947->130|1087->249|1135->262|1150->268|1289->386|1407->473
                    LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|26->5|26->5|28->7|28->7|31->10|33->12|33->12|36->15|42->21
                    -- GENERATED --
                */
            