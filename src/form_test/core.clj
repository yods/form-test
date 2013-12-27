(ns form-test.core
 (:use
   [compojure.handler :only [site]] ; form, query params decode; cookie; session, etc
   [compojure.core :only [defroutes GET POST DELETE ANY context]]
   [hiccup.page]
   [hiccup.form])

  (:require
   jig
   [compojure.route :as route]
   )
  (:import
   (jig Lifecycle)))


(defn sign-in [& error]
  [:div#sign-in
   (form-to {:class "form-set" :id "sign-in-form"}
            [:post "/signin"]
            [:h2.form-set-heading "Sign In"]
            (if error
              [:div.alert.alert-warning
               [:button.close {:type "button" :data-dismiss "alert"} "x"]
               error])
            (text-field {:class "form-control" :placeholder "email"} "email")
            (password-field {:class "form-control" :placeholder "password"} "pword")
            [:br]
            (submit-button {:class "btn btn-lg btn-primary pull-right"} "Sign In")
            (check-box "remember-me") (label "R" "Remember Me"))])


(defn login-handler [email pword]
  (cond
   (clojure.string/blank? email)
   (html5 (sign-in "Please enter email"))
   (clojure.string/blank? pword)
   (html5 (sign-in "please enter password"))
   :else
   (html5 (sign-in (str "email " email "pass ")))))

(defroutes home-routes
  (route/resources "/")
  (GET "/" [] (html5 (sign-in)))
  (POST "/signin" [email pword] (login-handler email pword))
  (route/not-found "Not Found"))


(deftype CompojureRoutes [config]
  Lifecycle
  (init [_ system]
    (update-in system [(:jig/id config) :jig.compojure/routes] conj home-routes))
  (start [_ system] system)
  (stop [_ system] system))
