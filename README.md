# juxt-csv

Processes a Land Registry monthly price paid data csv, outputting a csv file containing the top twenty total sales by town. Deletions and amendments in the input data are ignored, only new records for the month are processed.

## Usage

### Dependencies

Requires [Leiningen](http://leiningen.org/) 2.3.4 or greater. 

### Install

From the project root run:

	lein deps

Also [install the Midje Leiningen](https://github.com/marick/lein-midje) plugin to test.

### Test

From the project root run:

	lein midje 

### Use

From a repl:

	(require '[juxt-csv.core :refer :all])
	(run "input-file.csv")

Alternatively build with:

	lein uberjar
	
and run with:

	java -jar target/juxt-csv-0.1.0-SNAPSHOT-standalone.jar input-file.csv

## License

Copyright © 2014 Dave Spanton

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

Data found in the _/data_ directory is produced by Land Registry © Crown copyright 2014. This data covers the transactions received at Land Registry in the period of April 2014. See more at: [http://www.landregistry.gov.uk/market-trend-data/public-data/price-paid-faq#m24]()