package io.smallrye.graphql;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.jboss.arquillian.container.test.spi.client.deployment.AuxiliaryArchiveAppender;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import graphql.schema.GraphQLSchema;
import io.github.classgraph.ClassInfo;
import io.leangen.geantyref.GenericTypeReflector;
import io.leangen.graphql.GraphQLRuntime;
import nonapi.io.github.classgraph.types.Parser;

public class SmallRyeArchiveAppender implements AuxiliaryArchiveAppender {
    @Override
    public Archive<?> createAuxiliaryArchive() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                // spec classes
                .addPackages(true, GraphQLApi.class.getPackage())
                // smallrye impl
                .addPackages(true, GraphQLExtension.class.getPackage())
                // graphQL SPQR
                .addPackages(true, GraphQLRuntime.class.getPackage())
                // graphQL Java
                .addPackages(true, GraphQLSchema.class.getPackage())
                .addPackages(true, ClassInfo.class.getPackage())
                .addPackages(true, Parser.class.getPackage())
                .addPackages(true, GenericTypeReflector.class.getPackage())
                .addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension");

        return archive;
    }
}
