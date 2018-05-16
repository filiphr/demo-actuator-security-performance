# Demo project for determining some Spring Security performance measurements

 * `DemoActuatorAnySecurityApplicationTests` - measures how much time it takes for a simple request to be served when `EndpointRequest#toAnyEndpoint()` is used as a request matcher
 * `DemoActuatorBaseSecurityApplicationTests` - measures how much time it takes for a simple request to be served when `/actuator/**` is used as an ant matcher
 
 This is mostly as a playground and providing more info for https://github.com/spring-projects/spring-boot/issues/13183
 