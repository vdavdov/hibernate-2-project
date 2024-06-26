package by.vdavdov.util;

import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;

public class Liquibase {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Liquibase...");

        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
            CommandScope update = new CommandScope("update");

            update.addArgumentValue("changelogFile", "db/changelog.xml");
            update.addArgumentValue("url", "jdbc:mysql://localhost:3306/movie");
            update.addArgumentValue("username", "root");
            update.addArgumentValue("password", "mysql");
            update.execute();
        });

        System.out.println("Running Liquibase...DONE");
    }
}
