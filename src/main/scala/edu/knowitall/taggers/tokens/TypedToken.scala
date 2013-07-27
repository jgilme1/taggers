package edu.knowitall.taggers.tokens

import edu.knowitall.taggers.Type
import edu.knowitall.tool.stem.Lemmatized
import edu.knowitall.tool.chunk.ChunkedToken

case class TypedToken(token: Lemmatized[ChunkedToken], types: Set[Type]) {
  
  
  val typesBeginningAtToken =  types.filter(_.interval.start == token.offsets.start)
  val typesEndingAtToken = types.filter(_.interval.end == token.offsets.end)
  

}

object TypedToken{
    def makeTypedTokenFromJavaSet(token: Lemmatized[ChunkedToken], types: java.util.Set[Type]): TypedToken = {
    val scalaTypeSet = scala.collection.JavaConversions.collectionAsScalaIterable(types).toSet
    TypedToken(token, scalaTypeSet.filter(_.interval.intersects(token.offsets)))
  }
}