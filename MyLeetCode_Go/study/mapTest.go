package main

import "fmt"

// The animalSet type is a type alias of `map[string]struct{}`
type animalSet map[string]struct{}

// Adds an animal to the set
func (s animalSet) add(animal string) {
	s[animal] = struct{}{}
}

// Removes an animal from the set
func (s animalSet) remove(animal string) {
	delete(s, animal)
}

// Returns a boolean value describing if the animal exists in the set
func (s animalSet) has(animal string) bool {
	_, ok := s[animal]
	return ok
}

func main() {
	// Initializing zoo as a new set
	zoo := animalSet{}

	// Adding members to the set
	zoo.add("Walrus")
	zoo.add("Parrot")
	zoo.add("Lion")
	zoo.add("Giraffe")
	zoo.add("Bear")
	zoo.add("Bear")

	// Adding an existing member to the set again
	zoo.add("Lion")

	// Removing members from the set
	zoo.remove("Parrot")

	fmt.Println(zoo)
	// map[Bear:{} Giraffe:{} Lion:{} Walrus:{}]

	// Checking the presence of elements in a set
	fmt.Println(zoo.has("Penguin"))
	// false
	fmt.Println(zoo.has("Bear"))
	// true
}
