# Naming and Namespaces in SARL

[:Outline:]

In a SARL system, multiple elements are created at run-time, e.g. agents, behaviors, skills, spaces, contexts, etc.
Each of these elements is identified by an unique universal identifier (UUID).
This ensures that the identifiers are unique all the time.

Having access to the SARL elements above in a almost generic way is sometimes needed. For example, if you would like
to observe the values of the fields of an agent, you need first to refer to the agent itself.

In order to help the SARL developers to do this referring, a generic and common API is defined for naming the SARL elements.
This naming API is used by the namespace service in order to retrieve and reply the instances of the referred elements. 

## Naming of the SARL components

Basically the naming of the SARL element following the international standards of the
[Universal Resource Identifiers](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier), or URI in short.

### Background on URIs

An URI is a string of characters that unambiguously identifies a particular resource. To guarantee uniformity, all URIs follow a predefined set of syntax rules.
Such identification enables interaction with representations of the resource over a system or a network (typically the World Wide Web), using specific schemes.

Each URI following the general following format (where parts between brackets are optional):

		scheme:[//authority]path[?query][#fragment]

The URI generic syntax consists of a hierarchical sequence of five components:
* Scheme: A non-empty scheme component followed by a colon (`:`), consisting of a sequence of characters beginning with a letter and followed by any combination of letters, digits, plus (`+`), period (`.`), or hyphen (`-`). The scheme name refers to a specification for assigning identifiers within that scheme.
* Authority: An optional authority component preceded by two slashes (`//`) that specifies the user information to use to be connected to the referred resource.
* Path: A path component, consisting of a sequence of path segments separated by a slash (`/`). A path is always defined for a URI, though the defined path may be empty (zero length). A segment may also be empty, resulting in two consecutive slashes (`//`) in the path component.
* Query: An optional query component preceded by a question mark (`?`), containing a query string of non-hierarchical data. Its syntax is not well defined, but by convention is most often a sequence of attribute–value pairs separated by a delimiter.
* Fragment: An optional fragment component preceded by a hash (`#`). The fragment contains a fragment identifier providing direction to a secondary resource, such as a section heading in an article identified by the remainder of the URI.

This general URI syntax is refined in order to refer the SARL components.

### Naming for Agents

Each agent may be referred by an URI-based name in which the scheme is always `agent`. There is neither authority nor query part in the agent name.
 
The path of the agent name specifies the identification of the agent. You could refer an agent in three ways:

* Referring an agent whatever the context and spaces in which it is living. In this case, you have to specify only the agent identifier, e.g. `agent:a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c`. 
* Referring an agent only if it is living into a specific context. In this case, you have to specify the context identifier followed by the agent identifier, e.g. `agent:b9e6dcbc-d878-441d-afa1-35715950e22d/a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c`.
* Referring an agent only if it is living into a specific context and member of a specific space. In this case, you have to specify the context identifier followed by the space and agent identifiers, e.g. `agent:b9e6dcbc-d878-441d-afa1-35715950e22d/0bec6efd-12b1-4394-8e34-1b56e6b99c5c/a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c`. 

Where, `b9e6dcbc-d878-441d-afa1-35715950e22d` is the context identifier, `0bec6efd-12b1-4394-8e34-1b56e6b99c5c` is the space identifier, and
`a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c` is the agent identifier.

The fragment part is the name of an attribute/field that is declared into the referred agent. If the fragment part is specified, then the URI refers to the field itself.

The general syntax of the agent names is defined by the following BNF grammar:

		AGENT_NAME = "agent:" <ODSL> <CTX> <UUID> <FRG>
		ODSL = "/" OSL | <empty>
		OSL = "/" | <empty>
		CTX = <UUID> "/" SPC | <empty>
		SPC = <UUID> "/" | <empty>
		FRG = "#" <ID> | <empty>
		

### Naming for Behaviors

Each behavior of an agent may be referred by an URI-based name in which the scheme is always `behavior`. There is neither authority nor query part in the behavior name.
 
The path of the behavior name specifies the identification of the behavior. A behavior is always attached to an agent. That's why, the agent identifier is
mandatory in all cases. Consequently, for each of the three cases for referring an agent, two cases are defined for referring a behavior of those agent:

* Referring behavior from its fully qualified type name. In this case, you have to specify only the agent identifier followed by the fully qualified name of the behavior type, e.g. `behavior:a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c/mypackage.MyBehavior`. If the agent has multiple instance of the behavior, the first one is considered. 
* Referring behavior from its fully qualified type name and its index. This case enables to refer a specific behavior instance when the agent has multiple instances of the same behavior type. You have to specify the agent identifier followed by the fully qualified name of the behavior type and the index of the behavior instance (starting at zero), e.g. `behavior:a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c/mypackage.MyBehavior/1`. 

Where, `a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c` is the agent identifier.
`mypackage.MyBehavior` is the fully qualified name of the behavior type to refer. It must be a sub-type of the `io.sarl.lang.core.Behavior` type that is defined into the SARL API.

The fragment part is the name of an attribute/field that is declared into the referred behavior. If the fragment part is specified, then the URI refers to the field itself.

The general syntax of the behavior names is defined by the following BNF grammar (BNF rules in the previous section are re-used):

		BEHAVIOR_NAME = "behavior:" <ODSL> <CTX> <UUID> "/" <ID> <IDX> <FRG>
		IDX = "/" <INTEGER> | <empty>


### Naming for Skills

Each skill of an agent may be referred by an URI-based name in which the scheme is always `skill`. There is neither authority nor query part in the skill name.
 
The path of the skill name specifies the identification of the skill. A skill is always attached to an agent. That's why, the agent identifier is
mandatory in all cases. Moreover, a capacity must be implemented by only one skill within an agent. But a skill may implement multiple caapcities.
Consequently, for each of the three cases for referring an agent, one case is defined for referring a skill of those agent:

* Referring skill from the name of the capacity it is implementing. In this case, you have to specify only the agent identifier followed by the fully qualified name of the implemented capacity type, e.g. `skill:a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c/mypackage.MyCapacity`. 

Where, `a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c` is the agent identifier.
`mypackage.MyCapacity` is the fully qualified name of the capacity type that is implemented by the skill to refer. It must be a sub-type of the `io.sarl.lang.core.Capacity` type that is defined into the SARL API.

The fragment part is the name of an attribute/field that is declared into the referred skill. If the fragment part is specified, then the URI refers to the field itself.

The general syntax of the skill names is defined by the following BNF grammar (BNF rules in the previous sections are re-used):

		SKILL_NAME = "skill:" <ODSL> <CTX> <SPC> <UUID> "/" <ID> <FRG>

### Naming for Context

Each agent context may be referred by an URI-based name in which the scheme is always `context`. There is neither authority nor query part in the context name.
 
The path of the context name specifies the identification of the context. You could refer a context as:

* Referring a context from its identifier. In this case, you have to specify only the context identifier, e.g. `context:b9e6dcbc-d878-441d-afa1-35715950e22d`. 

Where, `b9e6dcbc-d878-441d-afa1-35715950e22d` is the context identifier.

The fragment part is the name of an attribute/field that is declared into the referred context. If the fragment part is specified, then the URI refers to the field itself.

The general syntax of the agent names is defined by the following BNF grammar (BNF rules in the previous section are re-used):

		CONTEXT_NAME = "context:" <ODSL> <UUID> <FRG>


### Naming for Space

Each space within a context may be referred by an URI-based name in which the scheme is always `space`. There is neither authority nor query part in the space name.
 
The path of the space name specifies the identification of the space. A space is always defined into a context. That's why, the context identifier is
mandatory in all cases. Consequently, for each of the cases for referring a context, you could refer a space as:

* Referring space from its identifier. In this case, you have to specify only the context identifier followed by the space identifier, e.g. `space:b9e6dcbc-d878-441d-afa1-35715950e22d/0bec6efd-12b1-4394-8e34-1b56e6b99c5c`. 

Where, `b9e6dcbc-d878-441d-afa1-35715950e22d` is the context identifier, and `0bec6efd-12b1-4394-8e34-1b56e6b99c5c` is the space identifier.

The fragment part is the name of an attribute/field that is declared into the referred space. If the fragment part is specified, then the URI refers to the field itself.

The general syntax of the space names is defined by the following BNF grammar (BNF rules in the previous section are re-used):

		SPACE_NAME = "space:" <ODSL> <UUID> "/" <UUID> <FRG>


### Naming for Service

A SRE may implement services. Each service may be referred by an URI-based name in which the scheme is always `service`.
There is neither authority nor query part in the service name.
 
The path of the service name specifies the identification of the service, i.e. its fully qualified name. You could refer a service with:

* Referring a service from its identifier. In this case, you have to specify only the service's fully qualitied name, e.g. `service:mypackage.MyService`. 

Where, `mypackage.MyService` is the fully qualified name of the object interface that describes the service.

The fragment part is the name of an attribute/field that is declared into the referred service. If the fragment part is specified, then the URI refers to the field itself.

The general syntax of the service names is defined by the following BNF grammar (BNF rules in the previous section are re-used):

		SERVICE_NAME = "service:" <ODSL> <ID> <FRG>



## Namespace Service

According to the [public API of the SRE](./SRE.md), it is possible to retrieve a service that is implemented and executed by the SRE.
A service dedicated to finding SARL elements into the SRE environment based on their names is defined into the SARL API. It is named the Namespace service.

The role of the Namespace service is to search for a SARL element based on a given name (as defined above).
This service explores the entire content of the SRE in order to find the requested element.
If an object that is corresponding to the given name is found, then the Namespace service replies the found object, or an accessor to its field if a fragment part was specified into the given name.

The Namespace service is defined as:

		[:ShowType:](io.sarl.api.naming.namespace.NamespaceService)

The functions `findObject` search for an object based on the given name (whatever it is an object of type `SarlName` representing the super-type of all the names, or a string representation of the URI).

To use this service, you have to get it from the SRE, as illustrated below:

		[:Success:]
			package io.sarl.docs.namespace
			import io.sarl.bootstrap.SRE
			import io.sarl.api.naming.namespace.NamespaceService
			class MyProgram {
				static def main(arguments : String*) {
					[:On]
					var bootstrap = SRE::getBootstrap
					var namingService = boostrap.getService(typeof(NamespaceService))
					var theAgent = namingService.findObject("agent:a7fbd4cc-9e1a-48c3-8ee8-3a7974ccb05c")
					[:Off]			
				}
			}
		[:End:]



[:Include:](../legal.inc)
