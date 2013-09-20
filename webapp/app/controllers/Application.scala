package controllers

import play.api.Play.current
import play.api._
import play.api.mvc._
import play.api.data
import play.api.data.Form
import play.api.data.Forms._
import scala.util.Random
import java.io.File
import edu.knowitall.taggers.tag.TaggerCollection

object Application extends Controller {

  def regexForm = Form(
      tuple(
          "path" -> text,
          "input" -> text
          )
          )
  
  def index = Action{
    val s = "New World"
    val adjectives = List("Brave","Brand","Grand","Terrific")
    Ok(Random.shuffle(adjectives).head + " " + s)
  }
  
  def index1 = Action{
    val content =  views.html.index1("james",List("hot dogs","coffee"))
    Ok(content)
  }
  
  def formApp = Action{
    Ok(views.html.form1(regexForm))
  }
  
  def show(id: String) = Action{
    Ok("Show boating! " + id + " boats")
  }
  
  /**
   * Handles the form submission.
   */
  def process = Action { implicit request =>
    regexForm.bindFromRequest.fold(
      formWithErrors => BadRequest("bad request"),
      {case (path, input) => {
        val pathToTaggers = new File(path)
        if(pathToTaggers.isDirectory()){
//          var x = ""
//          for(f: File <- pathToTaggers.listFiles()){
//              x += f.getName() + "\n"
//          }

          val t = TaggerCollection.fromPath(pathToTaggers.getPath())  
          Ok("it oks")
        }
        else{
          BadRequest("BAD REQUEST!")
        }
      }}
    )
  }



  
}
