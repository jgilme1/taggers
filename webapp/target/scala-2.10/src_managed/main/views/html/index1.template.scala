
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
object index1 extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,List[String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(customer: String, orders: List[String]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.42*/("""

<h1>Welcome """),_display_(Seq[Any](/*3.14*/customer)),format.raw/*3.22*/("""!</h1>

<ul>
"""),_display_(Seq[Any](/*6.2*/for(order <- orders) yield /*6.22*/ {_display_(Seq[Any](format.raw/*6.24*/("""
  <li>"""),_display_(Seq[Any](/*7.8*/order)),format.raw/*7.13*/("""</li>
""")))})),format.raw/*8.2*/("""
</ul>"""))}
    }
    
    def render(customer:String,orders:List[String]): play.api.templates.Html = apply(customer,orders)
    
    def f:((String,List[String]) => play.api.templates.Html) = (customer,orders) => apply(customer,orders)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Sep 19 15:34:01 PDT 2013
                    SOURCE: /scratch/sandbox/taggers/taggers/webapp/app/views/index1.scala.html
                    HASH: 433263b16978e3105d64f1f794bf641fde491ae0
                    MATRIX: 519->1|636->41|686->56|715->64|763->78|798->98|837->100|879->108|905->113|942->120
                    LINES: 19->1|22->1|24->3|24->3|27->6|27->6|27->6|28->7|28->7|29->8
                    -- GENERATED --
                */
            