{:jig/components

 {:test/routes
  {:jig/component form-test.core/CompojureRoutes
   ;; This puts routes in [:le-web/routes :jig.compojure/routes]

   }

  :test/compojure
  {:jig/component jig.compojure/HandlerComposer
   :jig/dependencies [:test/routes]
   ;; This goes through the dependencies, looking up [X :jig.compojure/routes]
   ;; to form routes, which it sticks in [:le-web/compojure :jig.ring/handler]
   :jig.web/context "/"}

  :test/server
  {:jig/component jig.http-kit/Server
   :jig/dependencies [:test/compojure]
   ;; This goes through the dependencies, until it finds the first [X :jig.ring/handler]
   :jig/project "../form-test/project.clj"
   :port 8080}}}
