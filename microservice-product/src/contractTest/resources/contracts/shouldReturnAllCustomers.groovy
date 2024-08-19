package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/customers'
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                [
                        id  : 1,
                        name: "Bob"
                ],
                [
                        id  : 2,
                        name: "Alice"
                ]
        ])
        headers {
            contentType('application/json')
        }
    }
}