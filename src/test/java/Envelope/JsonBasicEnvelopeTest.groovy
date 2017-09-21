package Envelope

import Provider.JsonEnvlope.JsonBasicEnvelope
import spock.lang.Specification

class JsonBasicEnvelopeTest extends Specification{

    JsonBasicEnvelope envelope

    def setup(){
        envelope = new JsonBasicEnvelope()
    }

    def "check if class exits"(){
        when:
        JsonBasicEnvelope envelope = new JsonBasicEnvelope()

        then:
        notThrown(NullPointerException)
    }

    def "#envlope status should mach the value that is set"(){
        when:
        envelope.setStatus("SUCCESS")

        then:
        envelope.getStatus() == "SUCCESS"

    }

    def "#envlope error message should mach the set value"(){
        setup:
        String errormsg = "This test returned no error so this should create a nice paradox"

        when:
        envelope.setErrorMsg(errormsg)

        then:
        envelope.getErrorMsg(errormsg)
    }

}
