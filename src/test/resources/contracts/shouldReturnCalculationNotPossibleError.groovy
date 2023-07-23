package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return calculation not possible error"
    request {
        method GET()
        url("/users/octocat_empty_followers") {

        }
    }
    response {
        status 409
    }
}