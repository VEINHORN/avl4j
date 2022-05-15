# avl4j

[![](https://jitpack.io/v/VEINHORN/avl4j.svg)](https://jitpack.io/#VEINHORN/avl4j)

Java implementation of the [AVL tree](https://en.wikipedia.org/wiki/AVL_tree) structure.

## Dependency

### Using JitPack

Step 1. Add the JitPack repository to your build file:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Step 2. Add the dependency

```xml
<dependency>
    <groupId>com.github.VEINHORN</groupId>
    <artifactId>avl4j</artifactId>
    <version>f6b7497a61</version>
</dependency>
```

## Usage

### Creation

```java
AVLTree = new AVLTree();
```

### Insertion

```java
tree.insert(3);
tree.insert(5);
tree.insert(10);
```

### Traverse

By default, AVL tree is traversed __in order__:

```java
tree.traverse();
```

Using custom __consumer__:

```java
tree.traverse(TraverseOrder.InOrder, consumer);
```

Using specific __order__ and custom __consumer__:

```java
tree.traverse(TraverseOrder.InOrder, consumer);
```

### Deletion

To delete specific node from the tree, just type:

```java
tree.delete(5);
```

## Related material

- https://en.wikipedia.org/wiki/AVL_tree
- https://zhjwpku.com/assets/pdf/AED2-10-avl-paper.pdf
- https://en.wikipedia.org/wiki/Tree_rotation
- https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
- https://www.geeksforgeeks.org/avl-tree-set-2-deletion/
- [Lecture 6: AVL Trees, AVL Sort](https://www.youtube.com/watch?v=FNeL18KsWPc)
- https://www.cs.usfca.edu/~galles/visualization/AVLtree.html

## Alternative Java solutions

- https://github.com/SurajSubramanian/AVL-Trees
- https://github.com/isaacmast/AVLTree