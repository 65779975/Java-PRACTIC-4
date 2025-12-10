module order.app {
    requires order.model;
    requires order.processing;
    requires order.storage;

    requires java.sql;
    requires javafaker;
    requires static lombok;
}