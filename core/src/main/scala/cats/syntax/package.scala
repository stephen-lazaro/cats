package cats

package object syntax {
  object all extends AllSyntax
  object apply extends ApplySyntax
  object bifunctor extends BifunctorSyntax
  object coflatMap extends CoFlatMapSyntax
  object comonad extends ComonadSyntax
  object contravariant extends ContravariantSyntax
  object flatMap extends FlatMapSyntax
  object foldable extends FoldableSyntax
  object functor extends FunctorSyntax
  object invariant extends InvariantSyntax
  object monadCombine extends MonadCombineSyntax
  object monadFilter extends MonadFilterSyntax
  object profunctor extends ProfunctorSyntax
  object show extends ShowSyntax
  object strong extends StrongSyntax
}
