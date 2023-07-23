package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return user data for given login"
    request {
        method GET()
        url("/users/octocat") {

        }
    }
    response {
        status 200
        body([
                "id"        : 583231,
                "login"     : "octocat",
                "name"      : "The Octocat",
                "type"      : "User",
                "avatarUrl" : "https://avatars.githubusercontent.com/u/583231?v=4",
                "createdAt" : "2011-01-25T18:44:36Z",
                "calculated": 0.006108113610913163
        ])
        bodyMatchers {
            jsonPath('$.id', byEquality())
            jsonPath('$.login', byEquality())
            jsonPath('$.name', byEquality())
            jsonPath('$.type', byEquality())
            jsonPath('$.avatarUrl', byEquality())
            jsonPath('$.createdAt', byEquality())
            jsonPath('$.calculated', byEquality())
        }
    }
}