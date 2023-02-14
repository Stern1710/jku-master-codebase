use std::{error::Error, fs::File, io::Read, num::ParseIntError, fmt::Display}; 

#[derive(Debug)]
enum ReadFromFileError {
    IOError(std::io::Error),
    ParseError(ParseIntError),
}

impl Error for ReadFromFileError {}

impl Display for ReadFromFileError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            ReadFromFileError::IOError(err) => write!(f, "IO Error! {err}"),
            ReadFromFileError::ParseError(err) => write!(f, " Parse error {err}"),
        }
    }
}

impl From<std::io::Error> for ReadFromFileError {
    fn from(err: std::io::Error) -> Self {
        ReadFromFileError::IOError(err)
    }
}

impl From<ParseIntError> for ReadFromFileError {
    fn from(err: ParseIntError) -> Self {
        ReadFromFileError::ParseError(err)
    }
}

fn read_number_from_file(path: Option<&str>) -> Result<u64, ReadFromFileError> {
    let mut file: File = File::open(path.unwrap_or("number.txt"))?;

    let mut buf: String = String::new();
    file.read_to_string(&mut buf)?;
    
    let num = buf.trim().parse()?;
    Ok(num)
}

fn main() -> Result<(), Box<dyn Error>>{
    match read_number_from_file(Some("does not exis.t")) {
        Ok(val) => println!("{val}"),
        Err(err) => eprintln!("{:?}", err),
    }
    Ok(())
}
