import { formatDistanceToNow, parseISO } from 'date-fns';
import { fr } from 'date-fns/locale';

export const formatRelativeDate = (isoDateString) => 
{
  if (!isoDateString) return '';
  const date = parseISO(isoDateString);
  return formatDistanceToNow(date, { addSuffix: true, locale: fr });
};
