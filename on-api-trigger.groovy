import groovy.json.*
import jenkins.model.Jenkins 
String jobName1= "test-repo"
String prNo= "PR-1"
def project= Jenkins.instance.getItem("test-repo")
def branchProject = project.getItem("PR-1")

if (branchProject.getLastBuild()){
                                hudson.security.ACL.impersonate(hudson.security.ACL.SYSTEM, {
                                    for (org.jenkinsci.plugins.workflow.support.steps.input.InputAction inputAction : branchProject.getLastBuild().getActions(org.jenkinsci.plugins.workflow.support.steps.input.InputAction.class)){
                                        for (org.jenkinsci.plugins.workflow.support.steps.input.InputStepExecution inputStep:inputAction.getExecutions()){
                                            println inputStep.getId()
                                            if (!inputStep.isSettled()){
                                                println inputStep.proceed(null)
                                            }
                                        }
                                    }
                                } as Runnable )
                            }