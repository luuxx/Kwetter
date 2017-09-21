package Envelope

import Provider.JsonEnvlope.JsonBasicEnvelope
import spock.lang.Specification

class JsonBasicEnvelopeTest extends Specification{
    def "check if class exits"(){
        when:
        JsonBasicEnvelope envelope = new JsonBasicEnvelope()

        then:
        notThrown(NullPointerException)
    }

    def "#envlope status should mach the value that is set"(){
        setup:
        JsonBasicEnvelope envelope = new JsonBasicEnvelope()

        when:
        envelope.setStatus("SUCCESS")

        then:
        envelope.getStatus() == "SUCCESS"

    }

    def "#envlope error message should mach the set value"(){
        setup:
        JsonBasicEnvelope  envelope = new JsonBasicEnvelope()
        String errormsg = "This test returned no error so this should create a nice paradox"

        when:
        envelope.setErrorMsg(errormsg)

        then:
        envelope.getErrorMsg(errormsg)
    }

}
