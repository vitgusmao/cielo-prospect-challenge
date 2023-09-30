import React from 'react';
import styled from 'styled-components';

import Typography from '../facades/Typography';

import Icon from './Icon';

const StyledTitle = styled(Typography.Title)`
  margin: 0;
  display: flex;
  flex-direction: row;
  align-items: center;
  column-gap: 8px;
`;

type TitleProps = {
  label: string;
  iconComponent?: Parameters<typeof Icon>[0]['component'];
  level?: 1 | 2 | 5 | 3 | 4 | undefined;
  className?: string;
  style?: React.CSSProperties;
};

function Title({
  label,
  iconComponent = undefined,
  level = 1,
  className = undefined,
  style = undefined,
}: TitleProps) {
  return (
    <StyledTitle level={level} className={className} style={style}>
      {iconComponent ? (
        <Icon
          component={iconComponent}
          aria-label={`Ícone do título: ${label}`}
        />
      ) : null}
      <span role="heading" aria-level={level}>
        {label}
      </span>
    </StyledTitle>
  );
}

export default Title;
