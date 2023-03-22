runApp: CHSearchApp.class
	java CHSearchApp

# targets to run tests

FrontendDeveloper_Tests: FrontendDeveloper_Tests.class
	java FrontendDeveloper_Tests

BackendDeveloper_Tests: BackendDeveloper_Tests.class
	java BackendDeveloper_Tests

# compiles working application code for all roles

CHSearchApp.class: CHSearchApp.java CHSearchFrontendFD.class
	javac CHSearchApp.java

CHSearchFrontendFD.class: CHSearchFrontendFD.java CHSearchFrontendInterface.java CHSearchBackendBD.class
	javac CHSearchFrontendFD.java CHSearchFrontendInterface.java

CHSearchBackendBD.class: CHSearchBackendBD.java CHSearchBackendInterface.java HashtableWithDuplicateKeysAE.class PostReaderDW.class
	javac CHSearchBackendBD.java CHSearchBackendInterface.java

HashtableWithDuplicateKeysAE.class: HashtableWithDuplicateKeysAE.java HashtableWithDuplicateKeysInterface.java HashtableMap.class
	javac HashtableWithDuplicateKeysAE.java HashtableWithDuplicateKeysInterface.java

HashtableMap.class: HashtableMap.java MapADT.java
	javac HashtableMap.java MapADT.java

PostReaderDW.class: PostReaderDW.java PostReaderInterface.java PostDW.class
	javac PostReaderDW.java PostReaderInterface.java

PostDW.class: PostDW.java PostInterface.java
	javac PostDW.java PostInterface.java

# compiles tests and placeholders

FrontendDeveloper_Tests.class: FrontendDeveloper_Tests.java CHSearchFrontendFD.class CHSearchBackendFD.class
	javac FrontendDeveloper_Tests.java

CHSearchBackendFD.class: CHSearchBackendFD.java CHSearchBackendInterface.java
	javac CHSearchBackendFD.java CHSearchBackendInterface.java

BackendDeveloper_Tests.class: BackendDeveloper_Tests.java CHSearchBackendBD.class HashtableWithDuplicateKeysBD.class PostReaderBD.class
	javac BackendDeveloper_Tests.java

HashtableWithDuplicateKeysBD.class: HashtableWithDuplicateKeysBD.java HashtableWithDuplicateKeysInterface.java
	javac HashtableWithDuplicateKeysBD.java HashtableWithDuplicateKeysInterface.java

PostReaderBD.class: PostReaderBD.java PostReaderInterface.java PostBD.class
	javac PostReaderBD.java PostReaderInterface.java

PostBD.class: PostBD.java PostInterface.java
	javac PostBD.java PostInterface.java

# clear out all compiled .class files and temporary~ files

clean:
	rm -f *.class
	rm -f *~
