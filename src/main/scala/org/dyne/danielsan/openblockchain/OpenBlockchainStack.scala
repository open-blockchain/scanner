package org.dyne.danielsan.openblockchain

//import org.scalatra.ScalatraServlet
//import org.scalatra.scalate.ScalateSupport
//
//trait OpenBlockchainStack extends ScalatraServlet with ScalateSupport {
//
//  notFound {
//    // remove content type in case it was set through an action
//    contentType = null
//    // Try to render a ScalateTemplate if no route matched
//    findTemplate(requestPath) map { path =>
//      contentType = "text/html"
//      layoutTemplate(path)
//    } orElse serveStaticResource() getOrElse resourceNotFound()
//  }
//
//}


import org.scalatra._
import scalate.ScalateSupport
import org.fusesource.scalate.{ TemplateEngine, Binding }
import org.fusesource.scalate.layout.DefaultLayoutStrategy
import javax.servlet.http.HttpServletRequest
import collection.mutable

import org.fusesource.scalate.util.IOUtil

trait OpenBlockchainStack extends ScalatraServlet with ScalateSupport {

  /* wire up the precompiled templates */
  override protected def defaultTemplatePath: List[String] = List("/WEB-INF/templates/views")
  override protected def createTemplateEngine(config: ConfigT) = {
    val engine = super.createTemplateEngine(config)
    engine.layoutStrategy = new DefaultLayoutStrategy(engine,
      TemplateEngine.templateTypes.map("/WEB-INF/templates/layouts/default." + _): _*)
    engine.packagePrefix = "templates"
    engine
  }
  /* end wiring up the precompiled templates */

  override protected def templateAttributes(implicit request: HttpServletRequest): mutable.Map[String, Any] = {
    super.templateAttributes ++ mutable.Map.empty // Add extra attributes here, they need bindings in the build file
  }


  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

    get("/api-docs/*") {
    val resourcePath = "/META-INF/resources/webjars/swagger-ui/2.0.21/" + params("splat")
    Option(getClass.getResourceAsStream(resourcePath)) match {
      case Some(inputStream) => {
        contentType = servletContext.getMimeType(resourcePath)
        IOUtil.loadBytes(inputStream)
      }
      case None => resourceNotFound()
    }
  }
}
