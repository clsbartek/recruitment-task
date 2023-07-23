package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return not found message for wrong user"
    request {
        method GET()
        url("/users/octocat_not_found") {

        }
    }
    response {
        status 404
    }
}