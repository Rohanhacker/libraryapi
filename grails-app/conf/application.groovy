grails {
	plugin {
		springsecurity {
			rest {
				oauth {
					frontendCallbackUrl = { String tokenValue -> "http://localhost:8080/auth/${tokenValue}" }
					facebook {
						client = org.pac4j.oauth.client.FacebookClient
						key = '1308333625862269'
						secret = '2d306e4b8cbe8485f5f552e31e22b582'
						scope = 'email,user_location'
						fields = 'id,name,email'
						defaultRoles = ['ROLE_USER', 'ROLE_FACEBOOK']
					}
				}
			}
		}
	}
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'library.Person'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'library.PersonRole'
grails.plugin.springsecurity.authority.className = 'library.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/**/**',   access: ['ROLE_USER'], httpMethod: 'PUT'],
	[pattern: '/**/**',   access: ['permitAll']],
	[pattern: '/**',   access: ['permitAll']]
]
//-anonymousAuthenticationFilter
grails.plugin.springsecurity.filterChain.chainMap = [
		//Stateless chain
		[
				pattern: '/api/**',
				filters: 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
		]
//		Traditional chain
//		[
//				pattern: '/**',
//				filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
//		]
]

