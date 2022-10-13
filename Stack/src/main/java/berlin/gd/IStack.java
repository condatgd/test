package berlin.gd;

import java.util.Optional;

public interface IStack<TElement>
{
    void push(TElement element);
    Optional<TElement> pop();
}