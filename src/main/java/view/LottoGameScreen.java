package view;

import domain.lotto.LottoNumber;
import util.OutputUtil;
import view.dto.LottoCountResponseDto;
import view.dto.LottoNumbersDto;
import view.dto.LottoResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGameScreen {
    public static final String BUY_STATUS = "%d개를 구매했습니다.";
    public static final String LOTTO_PREFIX = "[";
    public static final String LOTTO_POSTFIX = "]";
    public static final String DELIMITER = ", ";

    public void showLottoCount(final LottoCountResponseDto lottoCountResponseDto) {
        OutputUtil.printMessage(String.format(BUY_STATUS, lottoCountResponseDto.getLottoCount()));
    }

    public void showAllLottoStatus(final List<LottoResponseDto> lottoResponseDtos) {
        List<LottoNumbersDto> lottoNumbersDtos = lottoResponseDtos.stream()
                .map(lottoResponseDto -> lottoResponseDto.getLottoNumbersDto())
                .collect(Collectors.toList());

        lottoNumbersDtos.stream()
                .forEach(lottoNumberDto -> showLottoStatus(lottoNumberDto));
    }

    private void showLottoStatus(final LottoNumbersDto lottoNumbersDto) {
        List<LottoNumber> lottoNumbers = lottoNumbersDto.getLottoNumbers();
        String lottoStatus = makeSingleLottoStatus(lottoNumbers);
        OutputUtil.printMessage(lottoStatus);
    }

    private String makeSingleLottoStatus(final List<LottoNumber> lottoNumbers) {
        List<String> status = lottoNumbers.stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                .collect(Collectors.toList());
        return LOTTO_PREFIX + String.join(DELIMITER, status) + LOTTO_POSTFIX;
    }

}

