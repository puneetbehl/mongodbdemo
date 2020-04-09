package example

import grails.test.mongodb.MongoSpec

class PatientSpec extends MongoSpec {

    @Override
    protected List<Class> getDomainClasses() {
        [Patient, Address, HomeAddress]
    }

    void "test abstract embedded object"() {
        setup:
        Patient patient = new Patient(address: new HomeAddress(street: "123 St.", type: "HOME")).save(flush: true)

        expect:
        Patient.list()

        cleanup:
        Patient.deleteAll(patient)
    }
}
