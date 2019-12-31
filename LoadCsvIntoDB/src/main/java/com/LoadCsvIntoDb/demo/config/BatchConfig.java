package com.LoadCsvIntoDb.demo.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.LoadCsvIntoDb.demo.model.Hotels;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

//	@Autowired
//	public DataSource dataSource;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@192.168.1.148:1524:pdborcl");
		dataSource.setUsername("bajajha_dev");
		dataSource.setPassword("posidex");

		return dataSource;
	}

	@Bean
	public FlatFileItemReader<Hotels> reader() {
//		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//		tokenizer.setDelimiter("|");
//		tokenizer.setNames(new String[]{"id", "name"});
//
//		DefaultLineMapper lineMapper = new DefaultLineMapper<Hotels>();
	
		FlatFileItemReader<Hotels> reader = new FlatFileItemReader<Hotels>();
		reader.setResource(new ClassPathResource("hotels.csv"));
		reader.setLineMapper(new DefaultLineMapper<Hotels>() {
			{
				
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						
						setDelimiter("|");
						setNames(new String[] { "id", "name", "description",
								"city", "rating" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Hotels>() {
					{
						setTargetType(Hotels.class);
					}
				});

			}
		});

		return reader;
	}

	@Bean
	public DBLogProcessor processor() {
		return new DBLogProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Hotels> writer() {
		JdbcBatchItemWriter<Hotels> writer = new JdbcBatchItemWriter<Hotels>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Hotels>());
		writer.setSql("INSERT INTO HOTELS ( ID,NAME,DESCRIPTION,CITY,RATING) VALUES ( :id, :name, :description, :city, :rating )");
		writer.setDataSource(dataSource());

		return writer;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Hotels, Hotels> chunk(100)
				.reader(reader()).processor(processor()).writer(writer())
				.build();
	}

	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer()).flow(step1()).end()
				.build();
	}

}
