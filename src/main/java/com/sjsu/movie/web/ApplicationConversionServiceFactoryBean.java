package com.sjsu.movie.web;

import com.sjsu.movie.domain.Bill;
import com.sjsu.movie.domain.MemberType;
import com.sjsu.movie.domain.MembershipInfo;
import com.sjsu.movie.domain.Movie;
import com.sjsu.movie.domain.MovieRental;
import com.sjsu.movie.domain.MovieUser;
import com.sjsu.movie.service.BillService;
import com.sjsu.movie.service.MovieRentalService;
import com.sjsu.movie.service.MovieService;
import com.sjsu.movie.service.MovieUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	@Autowired
    BillService billService;

	@Autowired
    MovieService movieService;

	@Autowired
    MovieRentalService movieRentalService;

	@Autowired
    MovieUserService movieUserService;

	public Converter<Bill, String> getBillToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.movie.domain.Bill, java.lang.String>() {
            public String convert(Bill bill) {
                return new StringBuilder().append(bill.getBillId()).append(' ').append(bill.getUserId()).append(' ').append(bill.getBillStartDate()).append(' ').append(bill.getBillEndDate()).toString();
            }
        };
    }

	public Converter<Long, Bill> getIdToBillConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.movie.domain.Bill>() {
            public com.sjsu.movie.domain.Bill convert(java.lang.Long id) {
                return billService.findBill(id);
            }
        };
    }

	public Converter<String, Bill> getStringToBillConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.movie.domain.Bill>() {
            public com.sjsu.movie.domain.Bill convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Bill.class);
            }
        };
    }

	public Converter<MemberType, String> getMemberTypeToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.movie.domain.MemberType, java.lang.String>() {
            public String convert(MemberType memberType) {
                return new StringBuilder().append(memberType.getTypeName()).append(' ').append(memberType.getTotalMovies()).append(' ').append(memberType.getMemberFee()).toString();
            }
        };
    }

	public Converter<Long, MemberType> getIdToMemberTypeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.movie.domain.MemberType>() {
            public com.sjsu.movie.domain.MemberType convert(java.lang.Long id) {
                return MemberType.findMemberType(id);
            }
        };
    }

	public Converter<String, MemberType> getStringToMemberTypeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.movie.domain.MemberType>() {
            public com.sjsu.movie.domain.MemberType convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), MemberType.class);
            }
        };
    }

	public Converter<MembershipInfo, String> getMembershipInfoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.movie.domain.MembershipInfo, java.lang.String>() {
            public String convert(MembershipInfo membershipInfo) {
                return new StringBuilder().append(membershipInfo.getMembershipNumber()).append(' ').append(membershipInfo.getBalance()).append(' ').append(membershipInfo.getOutstandingMovies()).append(' ').append(membershipInfo.getCreatedDate()).toString();
            }
        };
    }

	public Converter<Long, MembershipInfo> getIdToMembershipInfoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.movie.domain.MembershipInfo>() {
            public com.sjsu.movie.domain.MembershipInfo convert(java.lang.Long id) {
                return MembershipInfo.findMembershipInfo(id);
            }
        };
    }

	public Converter<String, MembershipInfo> getStringToMembershipInfoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.movie.domain.MembershipInfo>() {
            public com.sjsu.movie.domain.MembershipInfo convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), MembershipInfo.class);
            }
        };
    }

	public Converter<Movie, String> getMovieToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.movie.domain.Movie, java.lang.String>() {
            public String convert(Movie movie) {
                return new StringBuilder().append(movie.getMovieName()).append(' ').append(movie.getCategory()).append(' ').append(movie.getReleaseDate()).append(' ').append(movie.getSummary()).toString();
            }
        };
    }

	public Converter<Long, Movie> getIdToMovieConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.movie.domain.Movie>() {
            public com.sjsu.movie.domain.Movie convert(java.lang.Long id) {
                return movieService.findMovie(id);
            }
        };
    }

	public Converter<String, Movie> getStringToMovieConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.movie.domain.Movie>() {
            public com.sjsu.movie.domain.Movie convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Movie.class);
            }
        };
    }

	public Converter<MovieRental, String> getMovieRentalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.movie.domain.MovieRental, java.lang.String>() {
            public String convert(MovieRental movieRental) {
                return new StringBuilder().append(movieRental.getRentedDate()).append(' ').append(movieRental.getReturnedDate()).append(' ').append(movieRental.getDueDate()).append(' ').append(movieRental.getComments()).toString();
            }
        };
    }

	public Converter<Long, MovieRental> getIdToMovieRentalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.movie.domain.MovieRental>() {
            public com.sjsu.movie.domain.MovieRental convert(java.lang.Long id) {
                return movieRentalService.findMovieRental(id);
            }
        };
    }

	public Converter<String, MovieRental> getStringToMovieRentalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.movie.domain.MovieRental>() {
            public com.sjsu.movie.domain.MovieRental convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), MovieRental.class);
            }
        };
    }

	public Converter<MovieUser, String> getMovieUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.movie.domain.MovieUser, java.lang.String>() {
            public String convert(MovieUser movieUser) {
                return new StringBuilder().append(movieUser.getUserName()).append(' ').append(movieUser.getPassword()).append(' ').append(movieUser.getFirstName()).append(' ').append(movieUser.getLastName()).toString();
            }
        };
    }

	public Converter<Long, MovieUser> getIdToMovieUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.movie.domain.MovieUser>() {
            public com.sjsu.movie.domain.MovieUser convert(java.lang.Long id) {
                return movieUserService.findMovieUser(id);
            }
        };
    }

	public Converter<String, MovieUser> getStringToMovieUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.movie.domain.MovieUser>() {
            public com.sjsu.movie.domain.MovieUser convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), MovieUser.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getBillToStringConverter());
        registry.addConverter(getIdToBillConverter());
        registry.addConverter(getStringToBillConverter());
        registry.addConverter(getMemberTypeToStringConverter());
        registry.addConverter(getIdToMemberTypeConverter());
        registry.addConverter(getStringToMemberTypeConverter());
        registry.addConverter(getMembershipInfoToStringConverter());
        registry.addConverter(getIdToMembershipInfoConverter());
        registry.addConverter(getStringToMembershipInfoConverter());
        registry.addConverter(getMovieToStringConverter());
        registry.addConverter(getIdToMovieConverter());
        registry.addConverter(getStringToMovieConverter());
        registry.addConverter(getMovieRentalToStringConverter());
        registry.addConverter(getIdToMovieRentalConverter());
        registry.addConverter(getStringToMovieRentalConverter());
        registry.addConverter(getMovieUserToStringConverter());
        registry.addConverter(getIdToMovieUserConverter());
        registry.addConverter(getStringToMovieUserConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
