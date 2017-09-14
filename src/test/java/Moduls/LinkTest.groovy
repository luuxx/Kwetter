package Moduls

import spock.lang.Specification

class LinkTest extends Specification {
    def "Test to see if class exits"(){
        when:
        Link link = new Link()

        then:
        notThrown(NullPointerException)
    }

    def "#link hraf should mach set value"(){
        setup:
        Link link = new Link();

        when:
        link.setHref("www.foobar.com")

        then:
        link.getHref() == "www.foobar.com"
    }

    def "#link rel should mach set value"(){
        setup:
        Link link = new Link()

        when:
        link.setRel("self")

        then:
        link.getRel() == "self"
    }

    def "Link constructed with both href and rel values"(){
        setup:
        String href = "www.foobar.com"
        String rel = "self"

        when:
        Link link = new Link(href,rel)

        then:
        link.getRel() == rel
        link.getHref() == href
    }
}
