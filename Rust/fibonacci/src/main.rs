struct Fibonacci {
    curr: u128,
    next: u128,
}

impl Default for Fibonacci {
    fn default() -> Self {
        Self {
            curr: 0,
            next: 1,
        }
        
    }
}

impl Iterator for Fibonacci {
    type Item = u128;

    fn next(&mut self) -> Option<Self::Item> {
        let new_next = self.curr.checked_add(self.next)?;

        self.curr = self.next;
        self.next = new_next;
        Some(self.curr)
        
    }
}

struct PrintExt<I> {
    inner: I,
}

impl<I> PrintExt<I> {
    fn new(iter: I) -> Self {
        Self{inner: iter}
    }
}

trait PrintInteratorExtension<I> {
    fn print_iter(self) -> PrintExt<I>;
}

impl<T> PrintInteratorExtension<T> for T where T: Iterator{
    fn print_iter(self) -> PrintExt<T> {
        PrintExt::new(iter: self)
    }
}

impl<I> Iterator for PrintExt<I> 
    where I: Iterator,
    I::Item: std::fmt::Debug,
    
{
    type Item = I::Item;

    fn next(&mut self) -> Option<Self::Item> {
        let item = self.next();
        println!("{:?}", item);
        item
    }
}

fn print_fibonacci(n: u16) {
    let fib: Fibonacci = Fibonacci::default();
    let mut sum_of_nums: u128 = 0;
    let mut sum_of_idx: usize = 0;
    for (idx, val) in fib.print_iter().take(n.into()).enumerate() {
        sum_of_idx += idx;
        sum_of_nums += val;
    }

    println!("{sum_of_idx} {sum_of_nums}");
}

fn main() {
    print_fibonacci(5);
}
