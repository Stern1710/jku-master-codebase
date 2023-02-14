use std::ops::Add;

#[derive(Debug)]
struct Kilometers(f32);

#[derive(Debug)]
struct Miles(f32);

impl<I> Add<I> for Kilometers where I: Into<f32> {
    type Output = Kilometers;

    fn add(self, rhs:I) -> Self::Output {
            Self(self.0 + rhs.into())
    }
}

impl From<f32> for Miles {
    fn from(val: f32) -> Self {
        Self(val)
    }
}

impl From<f32> for Kilometers {
    fn from(val: f32) -> Self {
        Self(val)
    }
}

impl Add<Kilometers> for Kilometers {
    type Output = Kilometers;

    fn add(self, rhs: Kilometers) -> Self::Output {
        Self(self.0 + rhs.0)
    }
}

fn main() {
    let km: Kilometers = 100.0.into();
    let km2: Kilometers = 500.0.into();

    let km3 = km + km2;

    let m1: Miles = 340.0.into();
}
