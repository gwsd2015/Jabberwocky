

fileName = raw_input("Enter file name: ");


files = ["commons-collections-3.2.1.jar",
"commons-configuration-1.6.jar",
"commons-lang-2.5.jar",
"commons-logging-1.1.1.jar",
"org.eclipse.core.contenttype_3.4.1.R35x_v20090826-0451.jar",
"org.eclipse.core.jobs_3.4.100.v20090429-1800.jar",
"org.eclipse.core.resources_3.5.2.R35x_v20091203-1235.jar",
"org.eclipse.core.runtime_3.5.0.v20090525.jar",
"org.eclipse.equinox.common_3.5.1.R35x_v20090807-1100.jar",
"org.eclipse.equinox.preferences_3.2.301.R35x_v20091117.jar",
"org.eclipse.jdt.core_3.5.2.v_981_R35x.jar","org.eclipse.osgi_3.5.2.R35x_v20100126.jar"]

comp = ""
sep = ":"
prefix = "lib/"
for i in range(len(files)):
    comp = comp+prefix+files[i]+sep



compileString = "javac -cp "+comp[0:len(comp)-1]+" "+fileName

print compileString
