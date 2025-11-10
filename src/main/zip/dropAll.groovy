/**
 * (c) Copyright IBM Corporation 2014, 2017.
 * This is licensed under the following license.
 * The Eclipse Public 1.0 License (http://www.eclipse.org/legal/epl-v10.html)
 * U.S. Government Users Restricted Rights:  Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.urbancode.air.plugin.helpers.NewAirPluginTool

def apTool = new NewAirPluginTool(this.args[0], this.args[1])

def workDir = new File('.').canonicalFile
def props = apTool.getStepProperties()
def inputPropsFile = new File(args[0])

def command = props['command']
def driver = props['driver']
def driverClasspath = props['driverClasspath']
def jdbcURL = props['jdbcURL']
def username = props['username']
def password = props['password']
def changeLogFile = props['changeLogFile']

println ' Data: command: ' + command
println ' Data: driver: ' + driver
println ' Data: driverClasspath: ' + driverClasspath
println ' Data: jdbcURL: ' + jdbcURL
println ' Data: username: ' + username
println ' Data: password: ' + password
println ' Data: changeLogFile: ' + changeLogFile
//println ' Data: tag: ' + tag
//println ' Data: rollbackType: ' + rollbackType
//println ' Data: preview: ' + preview

def lqcmd = command + " "
lqcmd = lqcmd + "--driver=" + driver + " "
lqcmd = lqcmd + "--classpath=" + driverClasspath + " "
lqcmd = lqcmd + " --url=" + jdbcURL + " "
lqcmd = lqcmd + "--username=" + username + " "
lqcmd = lqcmd + "--password=" + password + " "
lqcmd = lqcmd + "--changeLogFile=" + changeLogFile + " "
lqcmd = lqcmd + "dropAll"

println lqcmd

def proc = lqcmd.execute()
proc.waitForProcessOutput(System.out, System.out)
proc.waitFor()

System.exit proc.exitValue()
