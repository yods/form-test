(defproject form-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]

                 [compojure "1.1.5"]

                 ;; web server and async http provided by Jig (via extensions)
                 [jig/http-kit "1.7.0"]
                 [jig/compojure "1.7.0"]

                 [hiccup "1.0.3"]
])
