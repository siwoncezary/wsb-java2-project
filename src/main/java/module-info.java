module pl.wsb {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.media;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires java.logging;
    exports pl.wsb;
    exports pl.wsb.app.model;
    exports pl.wsb.apps;
    exports pl.wsb.exercises.app;
    exports pl.wsb.exercises.containers;
}