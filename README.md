# dw-code-exercise

## Assignment

Implement a TODO app in Clojure/ClojureScript. The basic architecture is: the backend is Clojure and should handle any state storage (CRUD operations). The front-end is responsible for rendering--that is, we expect you'll send data back and forth vs. rendered HTML to the browser (except for the initial page load which can be a static HTML file that runs the compiled CLJS code).

- The main page at the path "/" should contain the main TODO interface.
- There should be an "/about" page giving a description of the project.
- Per the architecture constraints, all rendering should happen on the client-side using a ClojureScript React wrapper library like Reagent, Rum, or any other of your choosing.
- A TODO consists of two values: completion state (a boolean value) and text description.
- Per the architecture constraints, TODO state should be stored on the backend.

### Features

Remember: Fewer of these done well beats more of them done poorly.

- Add a new TODO (initially incomplete)
- Mark a TODO as completed
- Unmark a TODO as completed (i.e. return it to incomplete state)
- Delete existing TODOs

Don't do any more than this. If you don't get through every bit of this in 2-4 hours, **DO NOT stress it, we are evaluating you on the work you did, not the work you didn't do.**

# todo

FIXME

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Testing

    $ lein test
    lein test todo.handler-test

    
