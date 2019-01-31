grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
        excludes 'grails-plugin-log4j' // because is duplicated with grails 2.5.6
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
        mavenRepo "http://repo.grails.org/grails/core"
        mavenRepo "http://repo.grails.org/grails/plugins/"
        mavenRepo "http://mavensync.zkoss.org/maven2/"
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.29'
        // runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
        //test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"
        
        
        // runtime 'mysql:mysql-connector-java:5.1.13'
        def zkVersion = "8.6.0.1"
        runtime "org.zkoss.zk:zk:${zkVersion}"
        runtime "org.zkoss.zk:zul:${zkVersion}"
        runtime "org.zkoss.zk:zkplus:${zkVersion}"
        runtime "org.zkoss.zk:zhtml:${zkVersion}"
        runtime "org.zkoss.zk:zkbind:${zkVersion}"
        runtime "org.zkoss.common:zel:${zkVersion}"
        
    }

    plugins {
        build(":release:3.1.2",
              ":rest-client-builder:2.1.1") {
            export = false
        }
        compile "org.grails.plugins:scaffolding:2.1.2"
    }
}
