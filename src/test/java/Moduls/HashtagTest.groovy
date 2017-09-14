package Moduls

import spock.lang.Specification

class HashtagTest extends Specification{
    def "Check if class exits"(){
        when:
        Hashtag hashtag = new Hashtag()

        then:
        notThrown(NullPointerException)
    }

    def "#hashtag id value should match set value"(){
        setup:
        Hashtag hashtag = new Hashtag()

        when:
        hashtag.setId(1)

        then:
        hashtag.getId() == 1
    }

    def "#hashtag Tag value should match set value"(){
        setup:
        Hashtag hashtag = new Hashtag()

        when:
        hashtag.setTag("#LiveLongAndProsper")

        then:
        hashtag.getTag() == "#LiveLongAndProsper"
    }
}
