# Introduction #

This page contains code snippets answering "how do I?" type of questions regarding the HyperGraphDB based OWL implementation. I've deliberately avoided extensive text outside code: explanations are in the form of comments.

# Getting Started #

### Create an OWL repository ###

An OWL repository is associated with HyperGraphDB instance. Depending on the type of functionality you want, you can create one of several variants of a repository:

  * `HGDBOntologyRepository` for a plain repository that just stores OWL data into a database
  * `VHGDBOntologyRepository` allows you to manage versions of an ontology and keep history with operations like commit, rollback etc.
  * `VDHGDBOntologyRepository` allows you to use collaborative versioning with other peers on the network, with operations such pull, push and everything you are familiar with distributed version control systems like GIT or Mercurial.

For example to create a versioned repository, just instantiate with a database location:

```
VHGDBOntologyRepository repo = new VHGDBOntologyRepository("/var/hypergraphdb/ontologydatabase");
```

Repositories are relatively light objects, so you can instantiate on a need by need basis. They do not open or close the databases. A database will be open on demand and then reused every time a repository is instantiated. As long as you provide the same database location, you don't have to worry.

### Import an Existing Ontology into Database ###

### Make an Ontology Versioned ###

Given an ontology already stored in a database, you can make versioned by first instantiating a versioning repository and then adding it to version control:

```
repo = new VHGDBOntologyRepository("/var/hypergraphdb/ontologydatabase");
manager = HGDBOWLManager.createOWLOntologyManager(repo);
HGDBOntology onto = (HGDBOntology)manager.createOntology(IRI.create("http://myonto"));
versionOnto = repo.addVersionControl(onto, "username");
```

That's all there is to it. The username provided as the 2nd argument to the `addVersionControl` method is just for the initial commit. It is not permanently associated with the ontology and each separate commit can be based on a separate username.

### Modify a Versioned Ontology ###

### Create a Version Branch ###